package com.example.tkumeeting;

import java.security.MessageDigest;

public class Encoder
{
	
	 StringBuffer a;
	
	 Encoder()
	 {
	     a = new StringBuffer();
	 }
	
	 private static String convertToHex(byte data[])
	 {
		 StringBuffer buf = new StringBuffer();
			for (int i = 0; i < data.length; i++) {
				int halfbyte = (data[i] >>> 4) & 0x0F;
				int two_halfs = 0;
				do {
					if ((0 <= halfbyte) && (halfbyte <= 9))
						buf.append((char) ('0' + halfbyte));
					else
						buf.append((char) ('a' + (halfbyte - 10)));
					halfbyte = data[i] & 0x0F;
				} while (two_halfs++ < 1);
			}
			return buf.toString();
	 }
	
	 public String encode(String s)
	 {
	     int i = s.length();
	     int j = 0;
	     do
	     {
	         if (j >= i)
	         {
	             return a.toString();
	         }
	         String s4;
	         String s1;
	         StringBuilder sb = null;
	         switch(Integer.toBinaryString(-32 + s.charAt(j)).toString().length())
	         {
	         case 6:
	             sb = new StringBuilder("0");
	             break;
	         case 5:
	        	 sb = new StringBuilder("00");
	        	 break;
	         default:
	        	 sb = new StringBuilder();
	        	 break;
	         }
	         s1 = sb.append(Integer.toBinaryString(-32 + s.charAt(j))).toString();
	         String s2 = (new StringBuilder()).append(s1.charAt(0)).append(s1.charAt(1)).toString();
	         String s3 = (new StringBuilder()).append(s1.charAt(6)).append(s1.charAt(5)).append(s1.charAt(4)).append(s1.charAt(3)).append(s1.charAt(2)).toString();
	         int k = 32 + Integer.parseInt((new StringBuilder(String.valueOf(s2))).append(s3).toString(), 2);
	         s4 = (new StringBuilder()).append((char)k).toString();
	         
	         if (s4.equals("!"))
	         {
	             s4 = "%21";
	         } else
	         if (s4.equals("#"))
	         {
	             s4 = "%23";
	         } else
	         if (s4.equals("%"))
	         {
	             s4 = "%25";
	         } else
	         if (s4.equals(")"))
	         {
	             s4 = "%29";
	         } else
	         if (s4.equals("-"))
	         {
	             s4 = "%2D";
	         } else
	         if (s4.equals("="))
	         {
	             s4 = "%3D";
	         } else
	         if (s4.equals("{"))
	         {
	             s4 = "%7B";
	         } else
	         if (s4.equals("|"))
	         {
	             s4 = "%7C";
	         } else
	         if (s4.equals("}"))
	         {
	             s4 = "%7D";
	         } else
	         if (s4.equals("~"))
	         {
	             s4 = "%7E";
	         } else
	         if (s4.equals("["))
	         {
	             s4 = "%5B";
	         } else
	         if (s4.equals("\\"))
	         {
	             s4 = "%5C";
	         } else
	         if (s4.equals("]"))
	         {
	             s4 = "%5D";
	         } else
	         if (s4.equals("^"))
	         {
	             s4 = "%5E";
	         } else
	         if (s4.equals("\""))
	         {
	             s4 = "%22";
	         } else
	         if (s4.equals("$"))
	         {
	             s4 = "%24";
	         } else
	         if (s4.equals("&"))
	         {
	             s4 = "%26";
	         } else
	         if (s4.equals("'"))
	         {
	             s4 = "%27";
	         } else
	         if (s4.equals("("))
	         {
	             s4 = "%28";
	         } else
	         if (s4.equals("*"))
	         {
	             s4 = "%2A";
	         } else
	         if (s4.equals("+"))
	         {
	             s4 = "%2B";
	         } else
	         if (s4.equals(","))
	         {
	             s4 = "%2C";
	         } else
	         if (s4.equals("."))
	         {
	             s4 = "%2E";
	         } else
	         if (s4.equals("/"))
	         {
	             s4 = "%2F";
	         } else
	         if (s4.equals(":"))
	         {
	             s4 = "%3A";
	         } else
	         if (s4.equals(";"))
	         {
	             s4 = "%3B";
	         } else
	         if (s4.equals("<"))
	         {
	             s4 = "%3C";
	         } else
	         if (s4.equals(">"))
	         {
	             s4 = "%3E";
	         } else
	         if (s4.equals("?"))
	         {
	             s4 = "%3F";
	         } else
	         if (s4.equals("@"))
	         {
	             s4 = "%40";
	         } else
	         if (s4.equals("_"))
	         {
	             s4 = "%5F";
	         } else
	         if (s4.equals("`"))
	         {
	             s4 = "%60";
	         } else
	         if (s4.equals(" "))
	         {
	             s4 = "%20";
	         }
	         a.append(s4);
	         j++;
	     } while (true);
	 }
	
	 public String md5(String s, String s1)
	 {
	     String s3;
	     try
	     {
	         long l = (Long.parseLong(s) + Long.parseLong(s1)) * Long.parseLong(s1);
	         String s2 = (new StringBuilder()).append(l).toString();
	         MessageDigest messagedigest = MessageDigest.getInstance("MD5");
	         messagedigest.update(s2.getBytes("iso-8859-1"), 0, s2.length());
	         s3 = convertToHex(messagedigest.digest()).toString().substring(0, 5);
	     }
	     catch (Exception exception)
	     {
	         exception.getMessage();
	         return "";
	     }
	     return s3;
	 }
 }