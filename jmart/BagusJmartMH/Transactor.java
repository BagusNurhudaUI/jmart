package BagusJmartMH;


/**
 * Write a description of class Transaction here.
 *
 * @author (bagus n)
 * @version (modul 3)
 */
abstract interface Transactor
{

    public boolean validate();

    public Invoice perform();
}
