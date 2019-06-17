package pl.org.seva.myapplication.main


class NieWiem(private val czego: String) : INieWiem {
    override fun nieWiew() {
        println("wiktor nie wiem $czego")
    }
}
