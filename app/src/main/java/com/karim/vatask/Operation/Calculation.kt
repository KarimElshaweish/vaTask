package com.karim.vatask.Operation

class Calculation {
    companion object{
        /**
         * @param fNumber the first parameter in the function that represent the result in the application UI
         * @param sNumber the second parameter in the function that represent the input number of the application UI
         * @return result This function return the summation of the two parameters
         */
        fun sum(fNumber:Double, sNumber:Double)= (fNumber+sNumber).toString()


        /**
         * @param fNumber the first parameter in the function that represent the result in the application UI
         * @param sNumber the second parameter in the function that represent the input number of the application UI
         * @return result This function return the subtraction of the first parameters of the second one
         */
        fun subtraction(fNumber: Double,sNumber: Double)=(fNumber-sNumber).toString()

        /**
         * @param fNumber the first parameter in the function that represent the result in the application UI
         * @param sNumber the second parameter in the function that represent the input number of the application UI
         * @return result This function return the multiplication of the two parameters
         */
        fun multiplication(fNumber: Double,sNumber: Double)=(fNumber*sNumber).toString()

        /**
         * @param fNumber the first parameter in the function that represent the result in the application UI
         * @param sNumber the second parameter in the function that represent the input number of the application UI
         * @return result This function return the division  of the first  parameters of the second one
         */
        fun division(fNumber: Double,sNumber: Double)=(fNumber/sNumber).toString()
    }
}