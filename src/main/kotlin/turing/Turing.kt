package pl.lodz.uni.project1.turing

class Turing private constructor(
    private val array: Array<L>, private var i: Int, private var state: S, private var iteration: Int = 1
) {
    companion object {
//        val x = 20

        operator fun invoke(number: Int): Turing {
//            val arr = Array(x * 2 + 3) { if (it < x || it >= x + number) L.EMPTY else L._0 }
            val arr = Array(number * 2 + 2) { if (it <= number || it > 2 * number) L.EMPTY else L._0 }
            return Turing(arr, number + 1, S.S0);
        }
    }

    private fun exec(move: M, newState: S = state, newValue: L = array[i]) {
        println("$iteration:\t(${state.value},${array[i].value})->(${newState.value},${newValue.value},${move.string})\t$this")
        iteration++
        array[i] = newValue
        state = newState
        i += move.value
    }

    override fun toString(): String {
        var s = ""
        for (j in array.indices) {
            if (j == i) {
                s += "S"
            }
            if (array[j] == L.EMPTY) {
                continue
            }
            s += array[j].value
        }
        return s
    }

    fun run() {
        while (true) {
            when (state) {
                S.S0 -> {
                    when (array[i]) {
                        L._0 -> exec(M.L, S.S1, L._0)
                        else -> throw IllegalArgumentException("(${S.S0},${array[i].value})")
                    }
                }

                S.S1 -> {
                    when (array[i]) {
                        L.EMPTY -> exec(M.L, S.S2, L.A)
                        else -> throw IllegalArgumentException("(${S.S1},${array[i].value})")
                    }
                }

                S.S2 -> {
                    when (array[i]) {
                        L.EMPTY -> exec(M.R, S.S3, L.A)
                        else -> throw IllegalArgumentException("(${S.S2},${array[i].value})")
                    }
                }

                S.S3 -> {
                    when (array[i]) {
                        L.A -> exec(M.R)
                        L._0 -> exec(M.L, S.S4)
                        else -> throw IllegalArgumentException("(${S.S3},${array[i].value})")
                    }
                }

                S.S4 -> {
                    when (array[i]) {
                        L.A -> exec(M.R, S.S5, L.B)
                        else -> throw IllegalArgumentException("(${S.S4},${array[i].value})")
                    }
                }

                S.S5 -> {
                    when (array[i]) {
                        L.B -> exec(M.R)
                        L._1 -> exec(M.R)
                        L._0 -> exec(M.L, S.S6, L._1)
                        L.EMPTY -> exec(M.L, S.S8)
                        else -> throw IllegalArgumentException("(${S.S5},${array[i].value})")
                    }
                }

                S.S6 -> {
                    when (array[i]) {
                        L.B -> exec(M.L)
                        L._1 -> exec(M.L)
                        L.A -> exec(M.R, S.S5, L.B)
                        L.EMPTY -> exec(M.R, S.S7)
                        else -> throw IllegalArgumentException("(${S.S6},${array[i].value})")
                    }
                }

                S.S7 -> {
                    when (array[i]) {
                        L.B -> exec(M.R, S.S7, L.A)
                        L._1 -> exec(M.L, S.S4)
                        else -> throw IllegalArgumentException("(${S.S7},${array[i].value})")
                    }
                }

                S.S8 -> {
                    when (array[i]) {
                        L._1 -> exec(M.L, S.S9, L._0)
                        L.C -> exec(M.R, S.Sk, L.C)
                        else -> throw IllegalArgumentException("(${S.S8},${array[i].value})")
                    }
                }

                S.S9 -> {
                    when (array[i]) {
                        L._1 -> exec(M.L)
                        L.C -> exec(M.L)
                        L.A, L.B -> exec(M.R, S.S10, L.C)
                        L.EMPTY -> exec(M.R, S.S11, L.A)
                        else -> throw IllegalArgumentException("(${S.S9},${array[i].value})")
                    }
                }

                S.S10 -> {
                    when (array[i]) {
                        L.C -> exec(M.R)
                        L._1 -> exec(M.R)
                        L._0 -> exec(M.L, S.S8)
                        else -> throw IllegalArgumentException("(${S.S10},${array[i].value})")
                    }
                }


                S.S11 -> {
                    when (array[i]) {
                        L.C -> exec(M.R, S.S11, L.A)
                        L._1 -> exec(M.R, S.S11, L._0)
                        L._0 -> exec(M.L)
                        L.A -> exec(M.R, S.S5, L.B)
                        else -> throw IllegalArgumentException("(${S.S11},${array[i].value})")
                    }
                }

                S.Sk -> return
            }
        }
    }
}