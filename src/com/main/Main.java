package com.main;

import java.util.Scanner;

import com.sistmng.instructor.InstructorMain;

public class Main {
	
	public static void main(String[] args) {
		
		InstructorMain im = new InstructorMain();
		
		
		Scanner sc = new Scanner(System.in);
		im.instructorMain(sc, "장혜진");
		
	}

}
