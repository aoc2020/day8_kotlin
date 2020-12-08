@file:JvmName("Main")

package com.github.aoc2020.day8_kotlin

import java.lang.IllegalStateException
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

fun try_all_mods(program:Array<Instruction>) : Int {
    for (i in program.indices) {
        var copy = program.copyOf()
        var new_i = when (copy[i].op) {
            "jmp" -> Instruction("nop",copy[i].param)
            "nop" -> Instruction("jmp",copy[i].param)
            else -> copy[i]
        }
        copy[i] = new_i;
        var vm = VM(copy);
        vm.run();
        if (vm.terminatedOk()) return vm.acc;
    }
    throw IllegalStateException("Did not find any valid vm")
}

fun main() {
    try {
        println("hello")
        var input = readFile("input.txt")
        var program = input.map { s -> Instruction(s) }.toTypedArray();
        var vm = VM(program)
        vm.run();
        println("Answer 1: ${vm.acc}")
        var answer2 = try_all_mods(program);
        println("Answer 2: $answer2")
    } catch(ex:Throwable) {
        println("$ex")
        ex.printStackTrace(System.out)
    }
}

fun readFile(fileName: String) : List<String> {
    return Files.lines(Path.of(fileName))
        .collect(Collectors.toUnmodifiableList())
}