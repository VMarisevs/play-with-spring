package com.vm.play.common


import ch.qos.logback.classic.pattern.ClassicConverter
import ch.qos.logback.classic.spi.ILoggingEvent
import kamon.Kamon
import kamon.context.Key
import kamon.trace.Span

class LogbackTenantConverter extends ClassicConverter {


  override def convert(event: ILoggingEvent): String = {
    val tenant = Kamon.currentContext().get(TenantSpan.ContextStringKey)

    if(tenant == None)
      "undefined"
    else
      tenant.get
  }

}

object TenantSpan {
  private val TENANT_HEADER = "X-Tenant"
  private val CORRELATION_ID_HEADER = "X-Correlation-Id"
  private val CORRELATION_ID = "correlationId"
  private val TENANT = "tenant"

  val ContextSpanKey = Key.broadcast[Span](TENANT, Span.Empty)
  val ContextStringKey = Key.broadcastString(TENANT)

}
