package com.agusmagne.shoplisttestapp

import java.io.IOException
import java.net.InetAddress
import java.net.Socket
import java.net.UnknownHostException
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

class TLSSocketFactory : SSLSocketFactory() {
    private val delegate: SSLSocketFactory

    @Throws(IOException::class)
    override fun createSocket(): Socket? {
        return enableTLSOnSocket(delegate.createSocket())
    }

    override fun getDefaultCipherSuites(): Array<String> = delegate.getDefaultCipherSuites()

    @Throws(IOException::class)
    override fun createSocket(
        s: Socket?,
        host: String?,
        port: Int,
        autoClose: Boolean
    ): Socket? {
        return enableTLSOnSocket(delegate.createSocket(s, host, port, autoClose))
    }

    @Throws(IOException::class, UnknownHostException::class)
    override fun createSocket(host: String?, port: Int): Socket? {
        return enableTLSOnSocket(delegate.createSocket(host, port))
    }

    @Throws(IOException::class, UnknownHostException::class)
    override fun createSocket(
        host: String?,
        port: Int,
        localHost: InetAddress?,
        localPort: Int
    ): Socket? {
        return enableTLSOnSocket(delegate.createSocket(host, port, localHost, localPort))
    }

    @Throws(IOException::class)
    override fun createSocket(host: InetAddress?, port: Int): Socket? {
        return enableTLSOnSocket(delegate.createSocket(host, port))
    }

    @Throws(IOException::class)
    override fun createSocket(
        address: InetAddress?,
        port: Int,
        localAddress: InetAddress?,
        localPort: Int
    ): Socket? {
        return enableTLSOnSocket(delegate.createSocket(address, port, localAddress, localPort))
    }

    override fun getSupportedCipherSuites(): Array<String> {
        TODO("Not yet implemented")
    }

    private fun enableTLSOnSocket(socket: Socket?): Socket? {
        if (socket != null && socket is SSLSocket) {
            (socket as SSLSocket).setEnabledProtocols(arrayOf("TLSv1.1", "TLSv1.2"))
        }
        return socket
    }

    init {
        val context: SSLContext = SSLContext.getInstance("TLS")
        context.init(null, null, null)
        delegate = context.getSocketFactory()
    }
}