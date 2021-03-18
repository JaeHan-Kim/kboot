package com.rude.kboot.dto

data class Sequence(
    val sequenceId: String,
    val numLen: Int,
    val currSeq: Int
) {
    fun nextVal(): String {
        return "%05d".format(this.currSeq) // String.format("%05d", this.currSeq).toLong()
    }
}
