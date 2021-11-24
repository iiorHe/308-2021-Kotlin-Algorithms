inline fun <reified T>ArrayAddValueIndex(arr : Array<T>, value : T, index : Int) : Array<T?> {
    var returnList: ArrayList<T> = ArrayList<T>();
    for(i in arr.indices){
        if(i == index){
            //add new value
            returnList.add(index, value);
            //for loop, append for index-array.count
            for(a in index..arr.count()){
                returnList.add(a+1, arr[a]);
            }
        }
        returnList.add(i,arr[i]);
    }
    var returnArr : Array<T?> = Array(returnList.size,{null});
    for(i in returnArr.indices){
        returnArr[i] = returnList.get(i);
    }
    return returnArr;
}
fun main() {
    var intArr : Array<Int> = arrayOf(1,2,3,56,1,7);
    var strArr : Array<String> = arrayOf("Hello","Welcome","home");
    intArr.forEach(println(it));
    strArr.forEach(println(it));
    intArr = ArrayAddValueIndex(intArr, 420, 3);
    intArr.forEach(println(it));
    println("Hello, world!!!")
}