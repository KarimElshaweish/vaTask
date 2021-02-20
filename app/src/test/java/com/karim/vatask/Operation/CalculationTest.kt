package com.karim.vatask.Operation

import org.junit.Assert.*
import org.junit.Test

class CalculationTest{
    @Test
    fun `check if the summation function is working well`(){
        val result=Calculation.sum(4.0,2.0)
        val actualResult:Double=6.0
        assertEquals(result,actualResult.toString())
    }


    @Test
    fun `check if the multiplication function is working well`(){
        val result=Calculation.multiplication(4.0,2.0)
        val actualResult:Double=8.0
        assertEquals(result,actualResult.toString())
    }

    @Test
    fun `check if the division function is working well`(){
        val result=Calculation.division(4.0,2.0)
        val actualResult:Double=2.0
        assertEquals(result,actualResult.toString())
    }

    @Test
    fun `check if the subtraction function is working well`(){
        val result=Calculation.subtraction(4.0,2.0)
        val actualResult:Double=2.0
        assertEquals(result,actualResult.toString())
    }

}