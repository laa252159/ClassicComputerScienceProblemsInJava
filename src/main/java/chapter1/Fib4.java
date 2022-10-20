// Fib4.java
// From Classic Computer Science Problems in Java Chapter 1
// Copyright 2020 David Kopec
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package chapter1;

public class Fib4 {

	public static long fib4(long n) {
		long last = 0, next = 1; // fib(0), fib(1)
		for (long i = 0; i < n; i++) {
			long oldLast = last;
			last = next;
			next = oldLast + next;
		}
		return last;
	}

	public static void main(String[] args) {
		System.out.println(fib4(5));
		System.out.println(fib4(400000000L));
	}
}
