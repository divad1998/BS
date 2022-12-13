package com.budgit.extensions;

import com.budgit.table.Patron;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import reactor.core.publisher.Mono;

import java.lang.reflect.Parameter;
import java.time.LocalDateTime;

/**
 *
 * Resolves Patron type parameters.
 */
public class PatronParameterResolver implements ParameterResolver {
    public Patron patron;

    PatronParameterResolver() {
        patron = new Patron();
        patron.setProfileMedia("$$$"); patron.setFirstName("David"); patron.setOtherNames("Chigozie");
        patron.setLastName("Dinneya");  patron.setCountry("Nigeria");
        patron.setState("Nasarawa"); patron.setLga("Karu"); patron.setCity("One man village");
        patron.setSex("Male"); patron.setCateringFor(1); patron.setEmail("divadchigozie@gmail.com");
        patron.setPassword("123456"); patron.setCreatedAt(LocalDateTime.parse("2022-12-03T18:29:20.200198928"));
    }
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return parameter.getType().getTypeName().equals("com.budgit.table.Patron");

    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return patron;
    }
}
