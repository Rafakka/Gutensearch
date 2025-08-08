package com.logos.gutensearch.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.logos.gutensearch.services.Literatura;

@Component
public class Principal {
    @Autowired
    private Literatura service;
    
    public void exibeMenu() {
        
        

        }
    }