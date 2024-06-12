package com.mrboomdev.awery.sdk.util;

import com.mrboomdev.awery.sdk.util.exceptions.InvalidSyntaxException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class FancyVersion implements Comparable<FancyVersion>, Serializable {
	@Serial
	private static final long serialVersionUID = 1;
	private final int lastPartWithNumbers;
	private final String[] args;
	private final String original;
    private final String prefix;

	public FancyVersion(String prefix, @NotNull String version) throws InvalidSyntaxException {
		this.args = test(prefix, version);
		this.original = version.trim();
		this.prefix = prefix;
		this.lastPartWithNumbers = getLastPartWithNumbers(args);
	}

	public FancyVersion(@NotNull String version) throws InvalidSyntaxException {
		this("", version);
	}

	public String getSuffix() {
		var numberVersionEndIndex = getNumberVersionEndIndex(prefix, original);
		return original.substring(numberVersionEndIndex);
	}

	public Integer getNumberPart(int partNumber) {
		if(partNumber > lastPartWithNumbers) {
			throw new IndexOutOfBoundsException("Part number exceeds number of valid \"number parts\" " + lastPartWithNumbers);
		}

		if(partNumber < 0 || partNumber >= args.length) {
			throw new IndexOutOfBoundsException("Part number can't be less than 0 or more than " + (args.length - 1));
		}

		return Integer.parseInt(args[partNumber]);
	}

	public String getStringPart(int partNumber) {
		if(partNumber < 0 || partNumber >= args.length) {
			throw new IndexOutOfBoundsException("Part number can't be less than 0 or more than " + (args.length - 1));
		}

		return args[partNumber];
	}

	private static int getLastPartWithNumbers(String @NotNull [] parts) {
		for(int i = parts.length - 1; i >= 0; i--) {
			var part = parts[i];

			if(part.matches("\\d+")) {
				return i;
			}
		}

		return -1;
	}

	@Override
	public int compareTo(@NotNull FancyVersion o) {
		return compare(this.original, o.original);
	}

	public boolean isGreaterThan(@NotNull FancyVersion other) {
		return compare(this.original, other.original) > 0;
	}

	public boolean isLessThan(@NotNull FancyVersion other) {
		return compare(this.original, other.original) < 0;
	}

	@Contract(pure = true)
	private static String @NotNull [] test(String prefix, @NotNull String input) throws InvalidSyntaxException {
		input = input.trim();

		if(!input.startsWith(prefix)) {
			throw new InvalidSyntaxException("The version number must start with '" + prefix + "'");
		}

		input = input.substring(prefix.length()).trim();

		var result = new String[input.length()];
		StringBuilder currentNumber = null;
		int partsCount = 0;

		for(int i = 0; i < input.length(); i++) {
			var character = input.charAt(i);

			if(character == '.') {
				if(currentNumber == null) {
					throw new InvalidSyntaxException("The version number must contain at least one number");
				}

				result[partsCount++] = currentNumber.toString();
				currentNumber = null;
				continue;
			}

			if(!Character.isDigit(character)) {
				if(currentNumber != null) {
					result[partsCount++] = currentNumber.toString();
					currentNumber = null;
				}

				break;
			}

			if(currentNumber == null) {
				currentNumber = new StringBuilder();
			}

			currentNumber.append(character);
		}

		if(currentNumber != null) {
			result[partsCount++] = currentNumber.toString();
		}

		var newResults = new String[partsCount];
		System.arraycopy(result, 0, newResults, 0, partsCount);

		return newResults;
	}

	private static int getNumberVersionEndIndex(@NotNull String prefix, @NotNull String version) {
		var prefixLength = prefix.length();

		for(int i = 0; i < version.length(); i++) {
			if(i < prefixLength) {
				continue;
			}

			var character = version.charAt(i);

			if(character == '.') {
				continue;
			}

			if(!Character.isDigit(character)) {
				return i;
			}
		}

		return version.length() - 1;
	}

	public static int compare(@NotNull String version1, @NotNull String version2) throws InvalidSyntaxException {
		return compare("", version1, version2);
	}

	public static int compare(String prefix, @NotNull String version1, @NotNull String version2) throws InvalidSyntaxException {
		var args1 = test(prefix, version1);
		var args2 = test(prefix, version2);

		var endIndex1 = getNumberVersionEndIndex(prefix, version1);
		var endIndex2 = getNumberVersionEndIndex(prefix, version2);

		for(int i = 0; i < args1.length; i++) {
			var arg1 = args1[i];
			var arg2 = args2[i];

			if(i == endIndex1 && i == endIndex2) {
				return 0;
			}

			if(i == endIndex1) {
				return 1;
			}

			if(i == endIndex2) {
				return -1;
			}

			var number1 = Integer.parseInt(arg1);
			var number2 = Integer.parseInt(arg2);

			if(number1 > number2) {
				return 1;
			}

			if(number1 < number2) {
				return -1;
			}
		}

		return 0;
	}

	public static boolean isValid(@NotNull String version) {
		return isValid("", version);
	}

	public static boolean isValid(String prefix, @NotNull String version) {
		try {
			test(prefix, version);
			return true;
		} catch(InvalidSyntaxException e) {
			return false;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FancyVersion other) {
			return other.original.trim().equals(this.original.trim());
		}

		return false;
	}

	public boolean equalsNumbers(Object obj) {
		if(obj instanceof FancyVersion other) {
			return compare(this.original, other.original) == 0;
		}

		return false;
	}

	@Override
	public String toString() {
		var builder = new StringBuilder("[ ");
		var iterator = Arrays.asList(args).iterator();

		while(iterator.hasNext()) {
			builder.append(iterator.next());

			if(iterator.hasNext()) {
				builder.append(", ");
			}
		}

		builder.append(" ]");
		return builder.toString();
	}
}