package ba.unsa.etf.rpr.domain;

public enum TypeOfMeal {

    Appetizer("Appetizer"),MainDish("Main dish"),Desert("Desert"),Drink("Drink");

    private String typeOfMeal;

    TypeOfMeal(String typeOfMeal){
        this.typeOfMeal = typeOfMeal;
    }
    @Override
    public String toString(){
        return typeOfMeal;
    }
}
