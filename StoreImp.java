package com;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

public class StoreImp implements Store 
{
	Scanner scan=new Scanner(System.in);
	private LinkedHashMap<Integer, String> strItemName=new LinkedHashMap<>();
	private LinkedHashMap<Integer, Double> strItemPrice=new LinkedHashMap<>();
	private LinkedHashMap<Integer, Integer> strItemQuantity=new LinkedHashMap<>();
	private LinkedHashMap<String, Double> promo=new LinkedHashMap<>();
	private LinkedHashMap<Integer, String> cstItemName=new LinkedHashMap<>();
	private LinkedHashMap<Integer, Double> cstItemPrice=new LinkedHashMap<>();
	private LinkedHashMap<Integer, Integer> cstItemQuantity=new LinkedHashMap<>();
	private String[] itemName= {"it1","it2","it3","it4"};
	private int[] id= {1,2,3,4};
	private double[] price= {100,150,180,200};
	double sum;
	private int[] quantity= {10,10,10,10};
	private String[] promoCode= {"pavan123","ghyt1523","htyu478"};
	private double[] discount= {25,50,75};
	private Set<Integer> set=strItemName.keySet();
	private Set<Integer> cset=cstItemName.keySet();

	public static void ItemNotFoundException()
	{
		try
		{
			throw new ItemNotFoundException("Item_Not_Found");
		}
		catch (ItemNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public static void InvalidCopen()
	{
		try
		{
			throw new InvalidCopen("Invalid_coupen");
		}
		catch (InvalidCopen e) 
		{
			System.out.println(e.getMessage());
		}
	}

	public  void addItem()
	{
		for(int i=0;i<itemName.length;i++)
		{
			strItemName.put(id[i],itemName[i]);
			strItemPrice.put(id[i], price[i]);
			
			strItemQuantity.put(id[i], quantity[i]);
		}
		for(int i=0;i<promoCode.length;i++)
		promo.put(promoCode[i],discount[i]);
	}

	public static void PromoCodeNotApplicableException()
	{
		try
		{
			throw new PromoCodeNotApplicableException("Discount Not Applicable");
		}
		catch (PromoCodeNotApplicableException e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void displayItems()
	{

		if(set==null)
		{
			ItemNotFoundException();
		}
		else
		{
			System.out.println("Item_Name"+"\t"+"RS"+"\t\t"+"Qty");
			for(int i:set)
			{
				System.out.println(strItemName.get(i)+"\t\t"+strItemPrice.get(i)+"\t\t"+strItemQuantity.get(i));
			}
		}
		System.out.println("\n");

	}

	@Override
	public void getDetails() 
	{
		System.out.println("enter item id");
		int id=scan.nextInt();
		if(strItemName.containsKey(id))
		{
			System.out.println("Item_Name"+"\t"+"RS"+"\t\t"+"Qty");
			System.out.println(strItemName.get(id)+"\t\t"+strItemPrice.get(id)+"\t\t"+strItemQuantity.get(id));
		}
		else
		{
			ItemNotFoundException();
		}
		System.out.println("\n");

	}

	@Override
	public void checkDiscount() 
	{
		System.out.println("enter Promo_Code");
		String promocode1=scan.next();
		if(promo.containsKey(promocode1))
		{
			System.out.println("enter Item id to know the Discount");
			int id=scan.nextInt();
			if(strItemName.containsKey(id))
			{
				ItemNotFoundException();
			}
			else
			{
				if(id%2==0) 
				{
					double price=strItemPrice.get(id);
					double discount=promo.get(promocode1);
					double total=price-((price*discount)/100);
					System.out.println("Discount Price is= "+total);
				}
				else
				{
					PromoCodeNotApplicableException();
				}
			}

			
		}
		else
		{
			InvalidCopen();
			System.out.println("\n");
		}

	}

	@Override
	public void bill() 
	{
		System.out.println("enter Promo_Code");
		String promocode2=scan.next();
		if(cset==null)
			ItemNotFoundException();
		else
		{
			System.out.println("Item_Name"+"\t"+"RS"+"\t\t"+"Discount"+"\t"+"Final_Price"+"\t\t"+"Qty");
			for(int i:cset)
			{
				if(i%2==0) 
				{
					double price=cstItemPrice.get(i);
					double discount=promo.get(promocode2);
					double total=price-((price*discount)/100);
					sum+=total;
					System.out.println(cstItemName.get(i)+"\t\t"+cstItemPrice.get(i)+"\t\t"+promo.get(promocode2)+"%"+"\t\t"+total+"\t\t\t"+strItemQuantity.get(i));
				}
				else
				{
					double price=cstItemPrice.get(i);
					sum+=price;
					System.out.println(cstItemName.get(i)+"\t\t"+cstItemPrice.get(i)+"\t\t"+"0"+"%"+"\t\t"+cstItemPrice.get(i)+"\t\t"+strItemQuantity.get(i));
				}

			}
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("The total amount to be paid= "+sum);
		}

	}
	public void cstAddItem()
	{
		System.out.println("enter Item Id to Add");
		int itemId=scan.nextInt();
		if(strItemName.containsKey(itemId))
		{
		System.out.println("enter Quantity");
		int quant=scan.nextInt();
		if(strItemQuantity.get(itemId)!=0&&quant<=strItemQuantity.get(itemId))
		{
			cstItemName.put(itemId, strItemName.get(itemId));
			int totalQuant=strItemQuantity.get(itemId);
			totalQuant-=quant;
			strItemQuantity.put(itemId,totalQuant);
			System.out.println("Item Added");
			cstItemPrice.put(itemId, strItemPrice.get(itemId));
			
		}
		else
		{
			try
			{
				throw new InsufficientStockException("Insufficient_Stock");
			}
			catch (InsufficientStockException e) 
			{
				System.out.println(e.getMessage());
			}

		}
		}
		else
			ItemNotFoundException();

	}
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		StoreImp str=new StoreImp();
		while(true)
		{
			str.addItem();
			System.out.println("Welcome To Store Managment");
			System.out.println("--------------------------------------------------");
			System.out.println("1: Display Store Items\n2: Select item to know the details");
			System.out.println("3: Check Discount\n4: Add items to cart\n5: Billing\n6: Exit");
			System.out.println("--------------------------------------------------");
			System.out.println("Enter Your Choice");
			int choice=scan.nextInt();
			switch(choice)
			{
			case 1:str.displayItems();
			break;
			case 2:str.getDetails();
			break;
			case 3:str.checkDiscount();
			break;
			case 4:str.cstAddItem();
			break;
			case 5:str.bill();
			break;
			case 6:System.out.println("THANKYOU......!");
			System.exit(0);
			break;
			default:
				System.out.println("Enter Valid Choice");
				break;

			}
		}

	}
}