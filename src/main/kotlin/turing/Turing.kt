package pl.lodz.uni.project1.turing

import pl.lodz.uni.project1.turing.Move.L
import pl.lodz.uni.project1.turing.Move.R
import pl.lodz.uni.project1.turing.State.*
import pl.lodz.uni.project1.turing.Value.*

class Turing private constructor(
    private val tape: Array<Value>, private var index: Int, private var state: State, private var iteration: Int = 1
) {
    companion object {
        /**
         * Creates a Turing machine (although the tape size is limited) for the given number
         *
         * Starts with:
         * - one empty cell
         * - empty cells equal to the number being checked (which will be filled with 'a', 'b', 'c' and will be used to compare with the cells holding the given number)
         * - cells filled with zeros equal to the number being checked (cells holding the given number)
         * - one empty cell
         */
        operator fun invoke(number: Int): Turing {
            val array = Array(number * 2 + 2) { if (it <= number || it > 2 * number) EMPTY else _0 }
            return Turing(array, number + 1, S0);
        }
    }

    private var currentValue
        get() = tape[index]
        set(value) {
            tape[index] = value
        }

    private val badArgument
        get() = IllegalArgumentException("(${state.value},${currentValue.value})")

    fun checkIfNumberIsPrime() {
        while (true) {
            when (state) {
                S0 -> {
                    when (currentValue) {
                        _0 -> nextIteration(R, S1)
                        else -> throw badArgument
                    }
                }

                S1 -> {
                    when (currentValue) {
                        _0 -> nextIteration(L)
                        EMPTY -> nextIteration(L, S2, A)
                        else -> throw badArgument
                    }
                }

                S2 -> {
                    when (currentValue) {
                        EMPTY -> nextIteration(R, S3, A)
                        else -> throw badArgument
                    }
                }

                S3 -> {
                    when (currentValue) {
                        A -> nextIteration(R)
                        _0 -> nextIteration(L, S4)
                        else -> throw badArgument
                    }
                }

                S4 -> {
                    when (currentValue) {
                        A -> nextIteration(R, S5, B)
                        else -> throw badArgument
                    }
                }

                S5 -> {
                    when (currentValue) {
                        B -> nextIteration(R)
                        _1 -> nextIteration(R)
                        _0 -> nextIteration(L, S6, _1)
                        EMPTY -> nextIteration(L, S9)
                        else -> throw badArgument
                    }
                }

                S6 -> {
                    when (currentValue) {
                        B -> nextIteration(L)
                        _1 -> nextIteration(L)
                        A -> nextIteration(R, S5, B)
                        EMPTY -> nextIteration(R, S7)
                        else -> throw badArgument
                    }
                }

                S7 -> {
                    when (currentValue) {
                        B -> nextIteration(R, S7, A)
                        _1 -> nextIteration(R)
                        _0 -> nextIteration(L, S8)
                        EMPTY -> nextIteration(L, S9, E)
                        else -> throw badArgument
                    }
                }

                S8 -> {
                    when (currentValue) {
                        _1 -> nextIteration(L)
                        A -> nextIteration(R, S5, B)
                        else -> throw badArgument
                    }
                }

                S9 -> {
                    when (currentValue) {
                        _1 -> nextIteration(L, S10, _0)
                        C -> nextIteration(R, Sk)
                        else -> throw badArgument
                    }
                }

                S10 -> {
                    when (currentValue) {
                        _1 -> nextIteration(L)
                        C -> nextIteration(L)
                        A, B -> nextIteration(R, S11, C)
                        EMPTY -> nextIteration(R, S12, A)
                        else -> throw badArgument
                    }
                }

                S11 -> {
                    when (currentValue) {
                        C -> nextIteration(R)
                        _1 -> nextIteration(R)
                        _0 -> nextIteration(L, S9)
                        else -> throw badArgument
                    }
                }

                S12 -> {
                    when (currentValue) {
                        C -> nextIteration(R, S12, A)
                        _1 -> nextIteration(R, S12, _0)
                        _0 -> nextIteration(L)
                        A -> nextIteration(R, S5, B)
                        else -> throw badArgument
                    }
                }

                Sk -> return
            }
        }
    }

    /**
     * - First argument is responsible for moving the pointer on the tape to the right or left
     * - Second argument changes the state we are currently in (if not specified the state will not be changed)
     * - Third argument changes the value of the cell we are currently in (if not specified the value will not be changed)
     *
     * The value and state are changed before the pointer is moved
     */
    private fun nextIteration(move: Move, newState: State = state, newValue: Value = currentValue) {
        println("$iteration:\t(${state.value},${currentValue.value})->(${newState.value},${newValue.value},${move.string})\t$this")
        iteration++
        currentValue = newValue
        state = newState
        index += move.value
    }

    override fun toString(): String {
        var s = ""
        for (i in tape.indices) {
            if (i == index) {
                s += "($state)"
            }
            s += tape[i].value
        }
        return s
    }
}