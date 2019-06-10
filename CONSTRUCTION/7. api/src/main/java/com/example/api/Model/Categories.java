package com.example.api.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Categories {
    private List<Cars> categories;
}
