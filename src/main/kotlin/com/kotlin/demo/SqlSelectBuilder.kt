package com.kotlin.demo

class SqlSelectBuilder {

    private lateinit var table: String

    fun from(table: String) {
        this.table = table
    }

    private val columns = mutableListOf<String>()

    fun select(vararg columns: String) {
        if (columns.isEmpty()) {
            throw IllegalArgumentException("At least one column should be defined")
        }
        if (this.columns.isNotEmpty()) {
            throw IllegalStateException("Detected an attempt to re-define columns to fetch. "
                    + "Current columns list: "
                    + "${this.columns}, new columns list: $columns")
        }
        this.columns.addAll(columns)
    }

    fun build(): String {
        if (!::table.isInitialized) {
            throw IllegalStateException("Failed to build an sql select - target table is undefined")
        }
        return toString()
    }

    override fun toString(): String {
        val columnsToFetch =
            if (columns.isEmpty()) {
                "*"
            } else {
                columns.joinToString(", ")
            }
        return "select $columnsToFetch from $table"
    }
}

/*
This is a function that returns nothing, takes in nothing but is invoked on an object of type Builder.
SqlSelectBuilder.()
The context of `this` has been set to the builder object and we can invoke functions declared within the builder.
 */
fun query(initializer: SqlSelectBuilder.() -> Unit): SqlSelectBuilder {
    return SqlSelectBuilder().apply(initializer)
}


val sql = query {
    select("column1", "column2")
    from ("myTable")
}.build()