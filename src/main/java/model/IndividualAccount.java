package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualAccount extends BaseAccount{
    private String firstName;
    private String lastName;

    public IndividualAccount(long id, String firstName, String lastName) {
        super(id, 1);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
