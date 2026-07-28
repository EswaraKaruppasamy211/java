import java.util.*;
class CityList1{
	ArrayList<String> city = new ArrayList<>();
	Scanner sc=new Scanner(System.in);
	void append()
	{
		city.add("chennai");
		city.add("Madurai");
		city.add("Salam");
		System.out.println("Enter a city to add:");
		city.add(sc.next());
	}
	void position()
	{
		System.out.println("Enter index:");
		int index=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter city to insert:");
		city.add(index,sc.nextLine());
	}
	void searching()
	{
		System.out.println("Enter city to search:");
		String search =sc.next();
		if(city.contains(search))
			System.out.println("City Fount");
		else
			System.out.println("City Not Fount");
	}
	void start()
	{
		System.out.println("Enter starting letter:");
		String ch=sc.next();
		System.out.println("Cities starting with"+ch+":");
		for(String c:city)
		{
			if(c.startsWith(ch))
			 System.out.println(c);
		}
	}
	int input()
	{
		System.out.println("Enter Choice:");
		return sc.nextInt();
		
	}
	void  display()
	{
		System.out.println(city);
	}
	public static void main(String[] args)
	{
		CityList1 obj= new CityList1();
		while(true)
		{
			int choice=obj.input();
			switch(choice){
			   case 1:
				obj.append();
				break;
			  case 2:
				obj.position();
				break;
			  case 3:
				obj.searching();
				break;
			  case 4:
				obj.start();
				break;
			   case 5:
				System.exit(0);
			}
			System.out.println("Current List:");
			obj.display();
		}
	}
}
		

		