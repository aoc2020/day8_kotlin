package com.github.aoc2020.day8_kotlin

class Instruction {

    val op : String;
    val param : Int;

    constructor(opcode:String, param:Int) {
        this.op = opcode;
        this.param = param;
    }

    constructor(line: String) {
        var regex = """([a-z]*) ([+-][0-9]+)""".toRegex()
        var match = regex.matchEntire(line)!!.destructured
        this.op = match.component1()
        this.param = match.component2().toInt()
    }
}