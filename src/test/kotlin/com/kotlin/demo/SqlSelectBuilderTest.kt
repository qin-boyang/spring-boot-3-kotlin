package com.kotlin.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SqlSelectBuilderTest {

    private fun doTest(expected: String, sql: SqlSelectBuilder.() -> Unit) {
        assertThat(query(sql).build()).isEqualTo(expected)
    }

    @Test
    fun `when no columns are specified then star is used`() {
        doTest("select * from table1") {
            from ("table1")
        }
    }
    @Test
    fun `when no condition is specified then correct query is built`() {
        doTest("select column1, column2 from table1") {
            select("column1", "column2")
            from ("table1")
        }
    }

}