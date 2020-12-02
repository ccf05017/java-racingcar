package com.nextstep.calculator.domain;

import com.nextstep.calculator.domain.exceptions.EmptyFormulaException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CalculatorFactory {
    private static final String FORMULA_SPLITTER = " ";

    public static Calculator of(String formula) {
        validateEmpty(formula);

        List<String> formulaContents = Arrays.asList(formula.split(FORMULA_SPLITTER));

        return new Calculator(extractOperators(formulaContents), extractNumbers(formulaContents));
    }

    private static void validateEmpty(String formula) {
        if (formula.trim().equals("")) {
            throw new EmptyFormulaException("문자열 수식이 반드시 존재해야 합니다.");
        }
    }

    private static List<Number> extractNumbers(List<String> formulaContents) {
        return formulaContents.stream()
                .filter(formulaContent -> !Operator.isOperator(formulaContent))
                .map(Number::of)
                .collect(Collectors.toList());
    }

    private static List<Operator> extractOperators(List<String> formulaContents) {
        return formulaContents.stream()
                .filter(Operator::isOperator)
                .map(Operator::of)
                .collect(Collectors.toList());
    }
}
