package pro.sky.java.TreeCategories.service;

import org.springframework.stereotype.Service;

@Service
public interface MyTreeServiceApi {
    public String addCategory(Long chat, String name);
}
