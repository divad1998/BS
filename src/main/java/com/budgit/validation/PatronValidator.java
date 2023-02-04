package com.budgit.validation;

import com.budgit.exception.BlankFieldException;
import com.budgit.exception.EmptyFieldException;
import com.budgit.exception.FieldValueOutOfRangeException;
import com.budgit.table.Patron;

/**
 *
 * Performs non-blank and non-empty validations after create-patron request
 */
public class PatronValidator {

    /**
     *Entry point of validation
     * @param patron {@link Patron} to validate.
     * @return validated {@link Patron}.
     */
    public Patron validate(Patron patron) {
            return nonEmpty(patron);
    }

    /**
     * No empty fields validator.
     *
     * @param patron {@link Patron} to validate.
     * @return Validated patron.
     */
    public Patron nonEmpty(Patron patron) {

        if (patron.getFirstName().equals("")) {
            throw new EmptyFieldException("First name ");
        }
        if (patron.getLastName().equals("")) {
            throw new EmptyFieldException("Last name ");
        }
        if (patron.getOtherNames().equals("")) {
            throw new EmptyFieldException("Other names ");
        }
        if (patron.getCountry().equals("")) {
            throw new EmptyFieldException("Country ");
        }
        if (patron.getState().equals("")) {
            throw new EmptyFieldException("State ");
        }
        if (patron.getLga().equals("")) {
            throw new EmptyFieldException("Lga ");
        }
        if (patron.getCity().equals("")) {
            throw new EmptyFieldException("City ");
        }
        if (patron.getSex().equals("")) {
            throw new EmptyFieldException("Sex ");
        }
        if (String.valueOf(patron.getCateringFor()).equals("")) {
            throw new EmptyFieldException("CateringFor ");
        }
        if (patron.getEmail().equals("")) {
            throw new EmptyFieldException("Email ");
        }
        if (patron.getPassword().equals("")) {
            throw new EmptyFieldException("Password ");
        }

        return nonBlank(patron);
    }

    /**
     *
     * Calls no blank field validator.
     */
    public Patron nonBlank(Patron patron) {
        if (isBlank(patron.getFirstName()))
            throw new BlankFieldException("First name ");

        if (isBlank(patron.getLastName()))
            throw new BlankFieldException("Last name ");

        if (isBlank(patron.getOtherNames()))
            throw new BlankFieldException("Other names ");

        if (isBlank(patron.getCountry()))
            throw new BlankFieldException("Country ");

        if (isBlank(patron.getState()))
            throw new BlankFieldException("State ");

        if (isBlank(patron.getLga()))
            throw new BlankFieldException("LGA ");

        if (isBlank(patron.getCity()))
            throw new BlankFieldException("City ");

        if (isBlank(patron.getSex()))
            throw new BlankFieldException("Sex ");

        if (isBlank(patron.getEmail()))
            throw new BlankFieldException("Email ");

        if (isBlank(patron.getPassword()))
            throw new BlankFieldException("Password ");

        return outOfRange(patron);
    }

    /**
     *
     * No-blank-field validator.
     * @param value Field's value to validate.
     * @return Whether non-blank or not.
     */
    public boolean isBlank(String value) {
        char chHolder = ' ';

        for (char ch : value.toCharArray()) {
            if (ch == ' ') { //c hray
                chHolder = ch;
            } else {
                chHolder = ch;
                break;
            }
        }

        return chHolder == ' ';
    }

    /**
     *
     * Validates size of each {@link Patron}'s property.
     * @param patron {@link Patron} to validate
     * @return Validated patron.
     */
    public Patron outOfRange(Patron patron) {
        if (patron.getProfileMedia() != null) {
            if (patron.getProfileMedia().length() > 36940)
                throw new FieldValueOutOfRangeException("ProfileMedia is too large.");
        }
        if (patron.getFirstName().length() > 20)
            throw new FieldValueOutOfRangeException("First name is too long.");
        if (patron.getLastName().length() > 20)
            throw new FieldValueOutOfRangeException("Last name is too long.");
        if (patron.getOtherNames().length() > 20)
            throw new FieldValueOutOfRangeException("Other Names are too long.");
        if (patron.getCountry().length() > 50)
            throw new FieldValueOutOfRangeException("Country is too long.");
        if (patron.getState().length() > 50)
            throw new FieldValueOutOfRangeException("State is too long.");
        if (patron.getLga().length() > 50)
            throw new FieldValueOutOfRangeException("LGA is too long.");
        if (patron.getCity().length() > 50)
            throw new FieldValueOutOfRangeException("City is too long.");
        if (patron.getSex().length() > 20)
            throw new FieldValueOutOfRangeException("Sex is too long.");
        if (patron.getCateringFor() > 255)
            throw new FieldValueOutOfRangeException("CateringFor is too long.");
        if (patron.getEmail().length() > 50)
            throw new FieldValueOutOfRangeException("Email is too long.");
        if (patron.getPassword().length() > 50)
            throw new FieldValueOutOfRangeException("Password is too long.");

        return patron;
    }
}