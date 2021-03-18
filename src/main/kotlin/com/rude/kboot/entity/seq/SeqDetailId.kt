package com.rude.kboot.entity.seq

import java.io.Serializable

class SeqDetailId : Serializable {
    private var seqId: String = ""
    private var seqKey: String = ""

    constructor() {}

    constructor(seqId: String, seqKey: String) : this() {
        this.seqId = seqId
        this.seqKey = seqKey
    }
}
