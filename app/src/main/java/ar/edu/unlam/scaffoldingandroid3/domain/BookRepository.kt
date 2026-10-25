package ar.edu.unlam.scaffoldingandroid3.domain

interface BookRepository {
    fun getBooks(): List<Book>
}
