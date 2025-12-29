package ro.univ.it_reservations.service.interpreter;

import org.springframework.stereotype.Component;
import java.util.Stack;

@Component
public class QueryParser {

    public Expression parse(String query) {
        if (query == null || query.trim().isEmpty()) {
            return equipment -> true;
        }

        Stack<Expression> stack = new Stack<>();
        String[] tokens = query.split("\\s+");

        for (String token : tokens) {
            if (token.contains(":")) {
                String[] parts = token.split(":");
                if (parts.length < 2) continue;

                String key = parts[0];
                String value = parts[1];

                if ("name".equalsIgnoreCase(key)) {
                    stack.push(new ContainsExpression(key, value));
                } else {
                    stack.push(new EqualsExpression(key, value));
                }
            }
        }

        if (stack.isEmpty()) {
            return equipment -> true;
        }

        Expression result = stack.pop();

        while (!stack.isEmpty()) {
            Expression next = stack.pop();
            result = new AndExpression(next, result);
        }

        return result;
    }
}