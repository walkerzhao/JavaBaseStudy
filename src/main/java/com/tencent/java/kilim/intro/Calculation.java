package com.tencent.java.kilim.intro;

import java.math.BigDecimal;

/**
 * java bean: 被除数、除数、result
 * @author andy
 *
 */
public class Calculation {
	 private BigDecimal dividend;
	 private BigDecimal divisor;
	 private BigDecimal answer;
	 
	 public Calculation(BigDecimal dividend, BigDecimal divisor) {
	  super();
	  this.dividend = dividend;
	  this.divisor = divisor;
	 }
	 
	 public BigDecimal getDividend() {
	  return dividend;
	 }
	 
	 public BigDecimal getDivisor() {
	  return divisor;
	 }
	 
	 public void setAnswer(BigDecimal ans){
	  this.answer = ans;
	 }
	 
	 public BigDecimal getAnswer(){
	  return answer;
	 }
	 
	 public String printAnswer() {
	  return "The answer of " + dividend + " divided by " + divisor +
	    " is " + answer;    
	 }
	}