package com.github.aoc2020.day8_kotlin

class VM {
    val program:Array<Instruction>;
    var acc = 0;
    var ip = 0;

    constructor(program:Array<Instruction>) {
        this.program = program;
    }

    fun terminated(): Boolean {
        return ip >= program.size && ip >= 0
    }

    fun terminatedOk(): Boolean {
        return ip == program.size
    }

    fun run() {
        var used: HashSet<Int> = HashSet();
        while (!terminated() && !used.contains(ip)) {
            val inst = program[ip];
            used.add(ip);
            when (inst.op) {
                "jmp" -> ip += inst.param
                "acc" -> {
                    acc += inst.param
                    ip += 1;
                }
                "nop" -> ip += 1
            }
        }
    }
}