package kotlin.random
enum class ShipClass{
    Patrol,
    Submarine,
    Destroyer,
    Battleship,
    Carrier,
}
data class Ship(val type : ShipClass, val dir : Boolean)

val freeCell = "."
val takenCell = "X"
val mapSizeX = 10
val mapSizeY = 10
var map = Array(mapSizeY) {Array(mapSizeX){freeCell}}

fun GenerateShips(count : Int, map : Array<Array<String>>) : Array<Array<String>>{
    var newmap = map
    var shipCounter : Int = 0
    while (shipCounter != count){
        val randShipY = (0..(mapSizeY-1)).shuffled().first()
        val randShipX = (0..(mapSizeX-1)).shuffled().first()
        val randType = (0..(ShipClass.values().size - 1)).shuffled().first()
        val randShip : Ship = Ship(ShipClass.values()[randType], Random.nextBoolean())
        //println("Attempt placement at: ${randShipX} ${randShipY} size ${randType + 1} hor ${randShip.dir}")
        if(CheckSpace(randShipX,randShipY,randShip,newmap)){
            if(randShip.dir){
                for(i in randShipX..(randShipX + randType)){
                    newmap[randShipY][i] = takenCell
                }
            } else {
                for(i in randShipY..(randShipY + randType)){
                    newmap[i][randShipX] = takenCell
                }
            }
            shipCounter += 1
        }
    }
    return newmap
}

fun CheckSpace(xPos : Int, yPos : Int, ship : Ship, map : Array<Array<String>>) : Boolean {
    //Horizontal case
    if(ship.dir){
        for(i in xPos..(xPos + ship.type.ordinal)){
            if(!CheckAdjacent(i,yPos,map)) return false
        }
    //Vertical case
    } else {
        for(i in yPos..(yPos + ship.type.ordinal)){
            if(!CheckAdjacent(xPos,i,map)) return false
        }
    }
    return true
}
fun CheckAdjacent(xPos : Int, yPos : Int, map : Array<Array<String>>) : Boolean {
    if(xPos >= mapSizeX || yPos >= mapSizeY) return false
    for(y in -1..1){
        for(x in -1..1){
            if(map.elementAtOrNull(yPos + y)?.elementAtOrNull(xPos + x) == takenCell ){
                //println("Illegal placement at: ${xPos + x} ${yPos + y}")
                return false
            }
        }
    }
    return true
}
fun <String> PrintMap(map : Array<Array<String>>){
    print("  ")
    for(i in 0..(mapSizeX-1)){
      print(i)
    }
    println()
    var row : Int = 0
    for(values in map){
        print("${row} ")
        for(value in values){
            print(value)
        }
        row += 1
        println()
    }
}
fun main(){
    var newmap = GenerateShips(10,map)
    PrintMap(newmap)
}