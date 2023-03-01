package pl.lodz.uni.project1

import pl.lodz.uni.project1.Pierwsza.S.*

class Pierwsza {
    companion object {
        fun liczbaPierwsza(liczba: Int) {
            val arr = Array(255) { if (it < 100 || it >= 100 + liczba) L.empty else L._0 }
            turing(arr, S0, 100)
        }

        private fun printArray(array: Array<L>, i: Int): String {
            var s = ""
            for (j in array.indices) {
                if (j == i) {
                    s += "S"
                }
                if (array[j] == L.empty) {
                    continue
                }
                s += array[j].s
            }
            return s
        }


        private fun turing(array: Array<L>, step: S, i: Int) {
            when (step) {
                S0 -> {
                    when (array[i]) {
                        L._0 -> {
                            println("(S0,0)->(S1,0,L) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S1, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S1 -> {
                    when (array[i]) {
                        L.empty -> {
                            println("(S1,[])->(S2,a,L) - ${printArray(array, i)}")
                            array[i] = L.A
                            turing(array, S2, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S2 -> {
                    when (array[i]) {
                        L.empty -> {
                            println("(S2,[])->(S2,a,R) - ${printArray(array, i)}")
                            array[i] = L.A
                            turing(array, S3, i + 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S3 -> {
                    when (array[i]) {
                        L.A -> {
                            println("(S3,a)->(S3,a,R) - ${printArray(array, i)}")
                            array[i] = L.A
                            turing(array, S3, i + 1)
                        }

                        L._0 -> {
                            println("(S3,0)->(S4,0,L) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S4, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S4 -> {
                    when (array[i]) {
                        L.A -> {
                            println("(S4,a)->(S5,b,R) - ${printArray(array, i)}")
                            array[i] = L.B
                            turing(array, S5, i + 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S5 -> {
                    when (array[i]) {
                        L.B -> {
                            println("(S5,b)->(S5,b,R) - ${printArray(array, i)}")
                            array[i] = L.B
                            turing(array, S5, i + 1)
                        }

                        L._1 -> {
                            println("(S5,1)->(S5,1,R) - ${printArray(array, i)}")
                            array[i] = L._1
                            turing(array, S5, i + 1)
                        }

                        L._0 -> {
                            println("(S5,0)->(S6,1,L) - ${printArray(array, i)}")
                            array[i] = L._1
                            turing(array, S6, i - 1)
                        }

                        L.empty -> {
                            println("(S5,[])->(S8,[],L) - ${printArray(array, i)}")
                            array[i] = L.empty
                            turing(array, S8, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S6 -> {
                    when (array[i]) {
                        L.B -> {
                            println("(S6,b)->(S5,b,L) - ${printArray(array, i)}")
                            array[i] = L.B
                            turing(array, S6, i - 1)
                        }

                        L._1 -> {
                            println("(S6,1)->(S5,1,L) - ${printArray(array, i)}")
                            array[i] = L._1
                            turing(array, S6, i - 1)
                        }

                        L.A -> {
                            println("(S6,a)->(S6,b,R) - ${printArray(array, i)}")
                            array[i] = L.B
                            turing(array, S5, i + 1)
                        }

                        L.empty -> {
                            println("(S6,[])->(S7,[],R) - ${printArray(array, i)}")
                            array[i] = L.empty
                            turing(array, S7, i + 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S7 -> {
                    when (array[i]) {
                        L.B -> {
                            println("(S7,b)->(S7,a,R) - ${printArray(array, i)}")
                            array[i] = L.A
                            turing(array, S7, i + 1)
                        }

                        L._1 -> {
                            println("(S7,1)->(S4,1,L) - ${printArray(array, i)}")
                            array[i] = L._1
                            turing(array, S4, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S8 -> {
                    when (array[i]) {
                        L._1 -> {
                            println("(S8,1)->(S9,0,L) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S9, i - 1)
                        }

                        L.empty -> {
                            println("(S8,[])->(Sk,[],L) - ${printArray(array, i)}")
                            array[i] = L.empty
                            turing(array, Sk, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S9 -> {
                    when (array[i]) {
                        L.A -> {
                            println("(S9,a)->(S9,c,R) - ${printArray(array, i)}")
                            array[i] = L.C
                            turing(array, S9, i + 1)
                        }

                        L.B -> {
                            println("(S9,b)->(S9,c,R) - ${printArray(array, i)}")
                            array[i] = L.C
                            turing(array, S9, i + 1)
                        }

                        L.C -> {
                            println("(S9,c)->(S9,c,R) - ${printArray(array, i)}")
                            array[i] = L.C
                            turing(array, S9, i + 1)
                        }

                        L._1 -> {
                            println("(S9,1)->(S9,1,R) - ${printArray(array, i)}")
                            array[i] = L._1
                            turing(array, S9, i + 1)
                        }

                        L._0 -> {
                            println("(S9,0)->(S8,0,L) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S8, i - 1)
                        }

                        L.empty -> {
                            println("(S9,[])->(S10,[],R) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S9, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S10 -> {
                    when (array[i]) {
                        L.C -> {
                            println("(S10,c)->(S10,b,R) - ${printArray(array, i)}")
                            array[i] = L.B
                            turing(array, S10, i + 1)
                        }

                        L._1 -> {
                            println("(S10,1)->(S10,0,R) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S10, i + 1)
                        }

                        L._0 -> {
                            println("(S10,0)->(S10,0,R) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S10, i + 1)
                        }

                        L.empty -> {
                            println("(S10,[])->(S11,[],L) - ${printArray(array, i)}")
                            array[i] = L.empty
                            turing(array, S11, i - 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                S11 -> {
                    when (array[i]) {
                        L._0 -> {
                            println("(S11,0)->(S11,0,L) - ${printArray(array, i)}")
                            array[i] = L._0
                            turing(array, S11, i - 1)
                        }

                        L.A -> {
                            println("(S11,a)->(S11,a,L) - ${printArray(array, i)}")
                            array[i] = L.A
                            turing(array, S11, i - 1)
                        }

                        L.empty -> {
                            println("(S11,[])->(S2,a,R) - ${printArray(array, i)}")
                            array[i] = L.A
                            turing(array, S2, i + 1)
                        }

                        else -> throw IllegalArgumentException()
                    }
                }

                Sk -> {
                    return
                }
            }
        }

    }

    private class Turing()
    private enum class L(val s: String) {
        A("a"), B("b"), C("c"), _0("0"), _1("1"), empty("[]")
    }

    private enum class S(val s: String) {
        S0("S0"), S1("S1"), S2("S2"), S3("S3"), S4("S4"), S5("S5"), S6("S6"), S7("S7"), S8("S8"), S9("S9"),
        S10("S10"), S11("S11"), Sk("Sk")
    }

    private enum class Move(val v: Int) { R(1), L(-1) }
}