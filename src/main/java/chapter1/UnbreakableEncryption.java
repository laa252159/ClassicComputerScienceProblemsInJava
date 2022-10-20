// UnbreakableEncryption.java
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class UnbreakableEncryption {
	// Generate *length* random bytes
	private static byte[] randomKey(int length) {
		byte[] dummy = new byte[length];
		Random random = new Random();
		random.nextBytes(dummy);
		return dummy;
	}

	public static KeyPair encrypt(String original) {
		return encrypt(original.getBytes());
	}

	public static KeyPair encrypt(byte[] original) {
		byte[] originalBytes = original;
		byte[] dummyKey = randomKey(originalBytes.length);
		byte[] encryptedKey = new byte[originalBytes.length];
		for (int i = 0; i < originalBytes.length; i++) {
			// XOR every byte
			encryptedKey[i] = (byte) (originalBytes[i] ^ dummyKey[i]);
		}
		return new KeyPair(dummyKey, encryptedKey);
	}

	public static String decrypt(KeyPair kp) {
		return new String(decryptToArray(kp));
	}

	public static byte[] decryptToArray(KeyPair kp) {
		byte[] decrypted = new byte[kp.key1.length];
		for (int i = 0; i < kp.key1.length; i++) {
			// XOR every byte
			decrypted[i] = (byte) (kp.key1[i] ^ kp.key2[i]);
		}
		return decrypted;
	}

	public static void main(String[] args) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get("/Users/sanix/IdeaProjects/ClassicComputerScienceProblemsInJava/src/main/resources/LC.png"));
		KeyPair kp = encrypt(encoded);
		File outputFile1 = new File("/Users/sanix/IdeaProjects/ClassicComputerScienceProblemsInJava/src/main/resources/LC_encoded.png");
		try (FileOutputStream outputStream = new FileOutputStream(outputFile1)) {
			outputStream.write(kp.key2);
		}
		byte[] decripted = decryptToArray(kp);
		File outputFile2 = new File("/Users/sanix/IdeaProjects/ClassicComputerScienceProblemsInJava/src/main/resources/LC_decoded.png");
		try (FileOutputStream outputStream = new FileOutputStream(outputFile2)) {
			outputStream.write(decripted);
		}
	}
}
