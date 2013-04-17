package com.teamdev.students.kasianov.calculator.data.functions;

import java.math.BigDecimal;

/**
 * Objects implement Comparable<Integer> interface providing compareTo(Integer o) method, where o is a arguments count
 * if the result of the compareTo is less than 0 -> then 'o' number of arguments is not enough for the function
 * if the result is 0 -> then 'o' number of arguments is enough for the function
 * if the result is more than 0 -> then 'o' number of arguments is too much for the function
 */
public interface Function {
    BigDecimal calculate(BigDecimal... arguments);
    int getMinimumArgumentsCount();
    int getMaximumArgumentsCount();
}
