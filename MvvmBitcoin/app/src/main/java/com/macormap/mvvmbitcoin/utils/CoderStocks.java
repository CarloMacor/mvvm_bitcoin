package com.macormap.mvvmbitcoin.utils;


public class CoderStocks {

   public static String codStock(int i) {
      String res = "";
      switch (i) {
         case  0 : res = "BTC";   break; // Bitcoin
         case  1 : res = "ETH";   break; // Ethereum
         case  2 : res = "XRP";   break; // Ripples
         case  3 : res = "LTC";   break; // Litecoin
         case  4 : res = "BCH";   break; // Bitcoin-Cash

         case  5 : res = "XMR";   break; // Monero
         case  6 : res = "IOT";   break; // IOTA


         case  7 : res = "USDT";  break; // Tether
         case  8 : res = "ETC";   break; // Ethereum-Classic
         case  9 : res = "DASH";  break; // Dash
         case 10 : res = "ZEC";   break; // Zcash
         case 11 : res = "EOS";   break; // EOS
         case 12 : res = "OMG";   break; // OmiseGo
         case 13 : res = "SAN";   break; // Santiment-Network-Token
         case 14 : res = "QTUM";  break; // Qtum
      }
      return res;
   }


   public static String descStockFromSym(String losymb) {
      String res = "";
      if (losymb.equals("BTC")) {res = descStock(0); }
      if (losymb.equals("ETH")) {res = descStock(1); }
      if (losymb.equals("XRP")) {res = descStock(2); }
      if (losymb.equals("LTC")) {res = descStock(3); }
      if (losymb.equals("BCH")) {res = descStock(4); }
      if (losymb.equals("XMR")) {res = descStock(5); }
      if (losymb.equals("IOT")) {res = descStock(6); }
      if (losymb.equals("USDT")) {res = descStock(7); }
      return res;
   }

   public static String descStock(int i) {
      String res = "";
      switch (i) {
         case  0 :  res = "Bitcoin";           break;  // BTC
         case  1 :  res = "Ethereum";          break;  // ETH
         case  2 :  res = "Ripples";           break;  // XRP
         case  3 :  res = "Litecoin";          break;  // LTC
         case  4 :  res = "Bitcoin-Cash";      break;  // BCS
         case  5 :  res = "Monero";            break;  // XMR
         case  6 :  res = "IOTA";              break;  // IOT
         case  7 :  res = "Tether";            break;  // USDT
         case  8 :  res = "Ethereum-Classic";  break;  // ETC
         case  9 :  res = "Dash";              break;  // DASH
         case 10 :  res = "Zcash";             break;  // ZEC
         case 11 :  res = "EOS";               break;  // EOS
         case 12 :  res = "OmiseGo";           break;  // OMG
         case 13 :  res = "Santiment";         break;  // SAN  Santiment-Network-Token
         case 14 :  res = "Qtum";              break;  // QTUM
      }
      return res;
   }






}


