package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class BfhlController {

    @PostMapping("/bfhl")
    public Map<String, Object> processData(@RequestBody Map<String, List<String>> request) {
        List<String> data = request.get("data");

        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialChars = new ArrayList<>();

        int sum = 0;
        StringBuilder alphabetsConcat = new StringBuilder();

        for (String item : data) {
            if (item.matches("-?\\d+")) { // numeric
                int num = Integer.parseInt(item);
                sum += num;
                if (num % 2 == 0) evenNumbers.add(item);
                else oddNumbers.add(item);
            } else if (item.matches("[a-zA-Z]+")) { // alphabets
                alphabets.add(item.toUpperCase());
                alphabetsConcat.append(item);
            } else {
                specialChars.add(item);
            }
        }

        // Build alternating caps reverse string
        StringBuilder concatStr = new StringBuilder();
        String allAlpha = alphabetsConcat.toString();
        boolean upper = true;
        for (int i = allAlpha.length() - 1; i >= 0; i--) {
            char c = allAlpha.charAt(i);
            concatStr.append(upper ? Character.toUpperCase(c) : Character.toLowerCase(c));
            upper = !upper;
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("is_success", true);
        response.put("user_id", "gunjshah_24102004".toLowerCase());
        response.put("email", "gunjshah2004@gmail.com");
        response.put("roll_number", "22BIT0195");
        response.put("odd_numbers", oddNumbers);
        response.put("even_numbers", evenNumbers);
        response.put("alphabets", alphabets);
        response.put("special_characters", specialChars);
        response.put("sum", String.valueOf(sum));
        response.put("concat_string", concatStr.toString());

        return response;
    }
}
