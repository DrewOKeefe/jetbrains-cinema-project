package cinema

const val capacity = 60
const val fullPrice = 10
const val reducedPrice = 8
var rows = 0
var seats = 0
var cinema = MutableList(rows) { MutableList(seats) { 'S' } }
var totalSeats = 0
var purchaseCount = 0
var income = 0
var totalIncome = 0
var percentage = 0.0

// FUNCTION TO SELECT CINEMA LAYOUT & UPDATE totalIncome:
fun cinemaLayout() {
    println("Enter the number of rows:")
    rows = readln().toInt()
    println("Enter the number of seats in each row:")
    seats = readln().toInt()
    cinema = MutableList(rows) { MutableList(seats) { 'S' } }
    totalSeats = rows * seats
    if (totalSeats > capacity) {
        totalIncome = (rows / 2 * seats * fullPrice) + ((rows - rows / 2) * seats * reducedPrice)
    } else {
        totalIncome = totalSeats * fullPrice
    }
}

// FUNCTION TO PRINT CINEMA LAYOUT:
fun cinemaPrint() {
    print("\nCinema:\n ")
    for (i in 1..seats) print(" $i")
    for (i in cinema.indices) print(
        "\n${i + 1} ${
            cinema[i].joinToString(
                " ")}")
    println()
    menuPrint()
}

// FUNCTION TO ENTER SEAT RESERVATION & UPDATE purchaseCount/percentage:
fun seatSelection() {
    while (true) {
        println("\nEnter a row number:")
        val rowSelection = readln().toInt()
        println("Enter a seat number in that row:")
        val seatSelection = readln().toInt()
        if (rowSelection > rows || seatSelection > seats) {
            println("Wrong input!")
            continue
        }
        if (cinema[rowSelection - 1][seatSelection - 1] == 'B') {
            println("That ticket has already been purchased!")
        } else {
        // FINDING PRICE FOR RESERVATION TICKET:
        val price =
            if (totalSeats <= capacity) fullPrice else if (rowSelection <= rows / 2) fullPrice else reducedPrice
        println("\nTicket price: $$price")
        income += price

        // CHANGE SEAT RESERVATION TO 'B' IN MUTABLE LIST:
        cinema[rowSelection - 1][seatSelection - 1] = 'B'
        purchaseCount++
        percentage = purchaseCount.toDouble() / totalSeats.toDouble() * 100
        break
    }
}
    menuPrint()
}

// FUNCTION TO CREATE STATISTICS LIST:
fun statistics() {
    val formatPercentage = "%.2f".format(percentage)
    println()
    println("""
        Number of purchased tickets: $purchaseCount
        Percentage: $formatPercentage%
        Current income: $$income
        Total income: $$totalIncome
    """.trimIndent())
    menuPrint()
}

// FUNCTION TO PRINT MENU:
fun menuPrint() {
    println()
    println(
        """
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
    """.trimIndent()
    )
    when (readln()) {
        "1" -> cinemaPrint()
        "2" -> seatSelection()
        "3" -> statistics()
        "0" -> return
    }
}

fun main() {
    cinemaLayout()
    menuPrint()
}

// PRICE PER TICKET:
//
//    val smallRoomProfit = totalSeats * fullPrice
//    val largeFrontProfit = rows / 2 * seats * fullPrice
//    val largeBackProfit = (rows - rows / 2) * seats * reducedPrice


//    val income =
//        if (totalSeats <= capacity) {
//            smallRoomProfit
//        } else {
//            largeFrontProfit + largeBackProfit
//        }
//
//    println()
//    println("Total income:")
//    println("$$income")
