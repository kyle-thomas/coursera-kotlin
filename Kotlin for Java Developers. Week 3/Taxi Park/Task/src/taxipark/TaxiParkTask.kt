package taxipark

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =
        this.allDrivers.filter { driver -> this.trips.none { it.driver == driver} }.toSet()

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        this.allPassengers.filter { passenger -> this.trips.filter { it.passengers.contains(passenger) }.size >= minTrips }.toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        this.allPassengers.filter { passenger -> this.trips.filter { it.driver == driver && it.passengers.contains(passenger) }.size > 1 }.toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        this.allPassengers.filter { passenger ->
            val partitions = this.trips.filter { it.passengers.contains(passenger) }.partition { it.discount != null && it.discount > 0 }
            partitions.first.size > partitions.second.size
        }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    val tenMinuteRange: Int = this.trips.groupBy { it.duration / 10 }.maxBy { (_,trips) -> trips.size }?.key ?: return null
    return IntRange(10 * tenMinuteRange, 10 * (tenMinuteRange + 1) - 1)
}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
//    val numDrivers: Int = this.allDrivers.size / 5
//    val driverIncomes = this.trips.groupBy({it.driver}, {it.cost}).map { it.value.sum() }.sortedDescending()
//    val totalCost = driverIncomes.sum() * .8
//    val topDrivers = if (numDrivers > driverIncomes.size) driverIncomes else driverIncomes.slice(0..numDrivers-1)
//    return !this.trips.isEmpty() && topDrivers.sum() >= totalCost
    if (trips.isEmpty()) return false

    val totalIncome = trips.sumByDouble { it.cost }
    val sortedDriversIncome: List<Double> = trips
            .groupBy(Trip::driver)
            .map { (_, tripsByDriver) -> tripsByDriver.sumByDouble {it.cost} }
            .sortedDescending()

    val numberOfTopDrivers = (0.2 * allDrivers.size).toInt()
    val incomeByTopDrivers = sortedDriversIncome
            .take(numberOfTopDrivers)
            .sum()

    return incomeByTopDrivers >= 0.8 * totalIncome

}