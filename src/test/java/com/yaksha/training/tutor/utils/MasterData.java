package com.yaksha.training.tutor.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.training.tutor.entity.Tutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    private static Random rnd = new Random();

    public static Tutor getTutor() {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        tutor.setName(randomStringWithSize(10));
        tutor.setSubject(randomStringWithSize(10));
        tutor.setDescription(randomStringWithSize(10));
        tutor.setDuration(randomNumberWithSize(1));
        tutor.setPrice(2300D);
        tutor.setIs_available(randomBoolean());
        return tutor;
    }

    public static List<Tutor> getTutorList(int size) {
        Long id = 0L;
        List<Tutor> tutors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Tutor tutor = new Tutor();
            tutor.setId(++id);
            tutor.setName(randomStringWithSize(10));
            tutor.setSubject(randomStringWithSize(10));
            tutor.setDescription(randomStringWithSize(10));
            tutor.setDuration(randomNumberWithSize(1));
            tutor.setPrice(2300D);
            tutor.setIs_available(randomBoolean());
            tutors.add(tutor);
        }
        return tutors;
    }


    public static Integer randomNumberWithSize(int size) {
        String alphabet = "123456789";
        return rnd.nextInt(alphabet.length());
    }

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
