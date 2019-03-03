package com.vm.play.common


import java.util.concurrent.TimeUnit

import akka.dispatch.{DispatcherPrerequisites, ExecutorServiceFactoryProvider, MessageDispatcher, MessageDispatcherConfigurator, _}
import com.typesafe.config.Config
import org.slf4j.MDC

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.{Duration, FiniteDuration}

class MDCPropagatingDispatcherConfigurator(config: Config, prerequisites: DispatcherPrerequisites)
  extends MessageDispatcherConfigurator(config, prerequisites) {

  private val instance = new MDCPropagatingDispatcher(
    this, config.getString("id"), config.getInt("throughput"),
    FiniteDuration(config.getDuration("throughput-deadline-time", TimeUnit.NANOSECONDS),TimeUnit.NANOSECONDS),
    configureExecutor(),
    FiniteDuration(config.getDuration("shutdown-timeout", TimeUnit.MILLISECONDS), TimeUnit.MILLISECONDS)
  )

  override def dispatcher(): MessageDispatcher = instance
}

class MDCPropagatingDispatcher(_configurator: MessageDispatcherConfigurator,
                               id: String,
                               throughput: Int,
                               throughputDeadlineTime: Duration,
                               executorServiceFactory: ExecutorServiceFactoryProvider,
                               shutdownTimeout: FiniteDuration
                              )
  extends Dispatcher(_configurator, id, throughput, throughputDeadlineTime, executorServiceFactory, shutdownTimeout) {

  self =>

  override def prepare(): ExecutionContext = new ExecutionContext {

    // capture the MDC
    val mdcContext = MDC.getCopyOfContextMap

    override def execute(runnable: Runnable): Unit = self.execute(new Runnable {
      override def run(): Unit = {
        // backup the callee MDC context
        val oldbMDCContext = MDC.getCopyOfContextMap

        // Run the runnable with the captured context
        setContextMap(mdcContext)
        try {
          runnable.run()
        } finally {
          // restore the callee MDC context
          setContextMap(oldbMDCContext)
        }

      }
    })

    override def reportFailure(cause: Throwable): Unit = self.reportFailure(cause)
  }

  private[this] def setContextMap(context: java.util.Map[String, String]): Unit ={
    if (context == null) {
      MDC.clear()
    } else {
      MDC.setContextMap(context)
    }
  }

}