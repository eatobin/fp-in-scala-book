def work(implicit i: Int): Unit = print(i)
implicit val v: Int = 2
work
