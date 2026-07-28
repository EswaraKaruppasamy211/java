import java.util.Scanner;
class EB1
{
	String cname, ctype ;
	int cno, pre, cur;
	void setInput()
	{
		Scanner s=new Scanner(System.in);
		System.out.println("Enter coustmer Name:");
		cname=s.nextLine();
		System.out.println("Enter coustmer Type:");
		ctype=s.nextLine();
		System.out.println("Enter coustmer No:");
		cno=s.nextInt();
		System.out.println("Enter previous Reading:");
		pre=s.nextInt();
		System.out.println("Enter current Reading:");
		cur=s.nextInt();
	}
	void call()
	{
		int c=cur-pre;
		int amount;

		if(ctype.equals("domestic"))
		{
			if(c <=100)
				amount=0;
			else if(c>100 && c<=200)
				amount=(c-100)*2;
			else if(c>200 && c<=500)
				amount=(c-200)*4+100*2;
			else
				amount=(c-500)*6+300*4+100*2;
		}
			
		else
		{
			if(c <=100)
				amount=c*2;
			else if(c>100 && c<=200)
				amount=(c-100)*4+100*2;
			else if(c>200 && c<=500)
				amount=(c-200)*6+100*4+100*2;
			else
				amount=(c-500)*7+300*6+100*4+100*2;
		}
		System.out.println("Unit Consumed:"+c);		
		System.out.println("Amount to be paid Rs. "+amount);
	
	}
	public static void main(String arg[])
	{
		EB1 b=new EB1();
		b.setInput();
		b.call();
	}
}