import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Encryption extends JFrame implements ActionListener
{
    JLabel lblEnter;
    JButton enc, dec, btnClear;
    JTextField txtEnter, txtEnc, txtDec;
    Container frame;

    public Encryption ()
    {
	super ("Encryption");

	frame = getContentPane ();
	lblEnter = new JLabel ("Enter text to encrypt:");
	txtEnter = new JTextField ("", 20);
	enc = new JButton ("Encrypt");
	txtEnc = new JTextField ();
	dec = new JButton ("Decrypt");
	txtDec = new JTextField ();
	btnClear = new JButton ("Clear");

	enc.addActionListener (this);
	dec.addActionListener (this);
	btnClear.addActionListener (this);

	frame.setLayout (new GridLayout (7, 1));
	frame.add (lblEnter);
	frame.add (txtEnter);
	frame.add (enc);
	frame.add (txtEnc);
	frame.add (dec);
	frame.add (txtDec);
	frame.add (btnClear);
	setSize (500, 1000); //sets size of frame
	show ();
    } // Constructor


    public void actionPerformed (ActionEvent e)
    {
	//runs text from user (first textbox) through encryption method and displays the returned variable in the second textbox
	if (e.getSource () == enc)
	{
	    String userText = txtEnter.getText ();
	    String code = encrypt (userText);
	    txtEnc.setText (code);
	}
	//runs text from second textbox through decryption method and displays it in last textbox
	else if (e.getSource () == dec)
	{
	    String userText2 = txtEnc.getText ();
	    String code2 = decrypt (userText2);
	    txtDec.setText (code2);
	}
	//clears all textboxes if user clicks on "Clear" button
	else if (e.getSource () == btnClear)
	{
	    txtEnter.setText ("");
	    txtEnc.setText ("");
	    txtDec.setText ("");
	}
    }


    public static void main (String[] args)
    {
	new Encryption ();      // Creates an Encryption frame
    } // main method


    public static boolean checkSpace (char let)
    {
	//method to check for space using ascii value
	
	int ascii = (int) let;
	
	//if a space is detected, return false, and the opposite in other cases
	if (ascii == 32)
	{
	    return false;
	}
	else
	{
	    return true;
	}
    }


    public static String encrypt (String text)
    {
	// declares variables
	int leng, ascii2;
	String text2 = "";
	char let2;
	leng = text.length ();
	int count = 0;

	for (int i = 0 ; i <= leng - 1 ; i++) //loop used to go through each letter
	{
	    //declares for loop count variable as letter position of user's inputted text
	    let2 = text.charAt (i);
	    //finds ascii value of character
	    ascii2 = (int) let2; 
	    //variable to be used in encryption algorithm
	    count = count * 3;
	    
	    //if "checkSpace" returns true with the letter, the ascii value is encrypted using an algorithm
	    if (checkSpace (let2) == true)
	    {
		int ascii3 = eKey(ascii2) - count; //puts ascii value through "dKey" method and subtracts it by the previously mentioned variable ("to be used in... algorithm")
		//if statement so the ascii value is never 975 (to avoid box as char)
		if (ascii3 == 975)
		{ 
		    ascii3 = ascii3 + 1;
		}
		//converts encrypted ascii value to a char
		char let3 = (char) ascii3;
		//System.out.println(ascii3 + "\n");
		
		//adds encrypted char to output text variable and a random char next to it
		text2 = text2 + let3 + (char)(Math.random() * 93 + 33);
	    }
	    else
	    {
		text2 = text2 + let2 + " "; //if a space is detected, two spaces are outputted (needed for decryption as an even number of spaces are needed)
	    }
	}
	return (text2); //returns the final encrypted String variable
    }


    public static String decrypt (String text3)
    {
	// declares variables
	int leng2, ascii4;
	String text4 = "";
	String text5;
	char let4;
	leng2 = text3.length ();
	int count1 = 0;
	
	//loop used to go through each letter of encrypted message
	for (int d = 0 ; d <= leng2 - 1 ; d++)
	{
	    //uses loop count as reference for position of char in encrypted message String
	    let4 = text3.charAt (d);
	    //finds ascii value of char
	    ascii4 = (int) let4;
	    //variable to be used in encryption algorithm
	    count1 = count1 * 3;
	    
	    // if letter is a space, nothing changes
	    if (ascii4 == 32)
	    {
		text4 = text4 + let4;
	    }
	    // starting from the first char, every second char has the decryption algorithm used on it
	    else if (d % 2 == 0)
	    {
		int ascii5 = ascii4 + count1;
		int ascii6 = dKey(ascii5); //puts ascii value through "dKey" method
		char let5 = (char) ascii6; //converts ascii value back to a char
		text4 = text4 + let5; //adds char to decryption String variable
	    }
	    //all the other chars (random generated ones) are erased
	    else
	    {
		text4 = text4 + "";
	    }
	}
	return (text4.replaceAll ("  ", " ")); //returns decrypted message with half the spaces to counteract the spaces added in encryption program
    }


    public static int eKey (int asc)
    {
	//method to randomize a number and add to the ascii value after it was multiplied by ten
	Random rand = new Random ();
	int num = rand.nextInt (9);
	int key = (asc*10) + num;
	return (key); //returns sum
    }


    public static int dKey (int asc2)
    {
	//decryption method to counteract "ekey" method
	int key2 = asc2/10; //divides by ten to get back original ascii value (because it is an int variable it rounds back to the original)
	return (key2); // returns quotient
    }
} // Encryption class
