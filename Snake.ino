#include <EEPROM.h>
#include <Adafruit_GFX.h>
#include <Adafruit_PCD8544.h>
#include <Wire.h>

Adafruit_PCD8544 display = Adafruit_PCD8544(7, 6, 5, 4, 3); 
//#define LEFT 8
//#define DOWN 10
//#define RIGHT 11
//#define UP 12
//#define PAUSE 9

#define MAX_WIDTH 84   
#define MAX_HEIGHT 48
#define speakerPin 2


// Define the pin numbers
const int swPin = 13;
const int VrxPin = A5;
const int VryPin = A0;

// Set the initial variable values
int xDirection = 0;
int yDirection = 0;
int switchState = 1;

boolean dl=false,dr=false,du=false,dd=false; 
int x[200],y[200],i,slength,tempx=10,tempy=10,xx,yy;
unsigned int high;
uint8_t bh,bl;
int xegg,yegg;
int freq,tb;
int l,r,u,d,p;
unsigned long time=280,beeptime=50;
int score=0,flag=0; 
void setup()
{
//  EEPROM.write(1,0);
//  EEPROM.write(0,0);
  
  Serial.begin(9600); 
  pinMode(swPin, INPUT);
 // Wire.begin();

  pinMode(11, OUTPUT);
  pinMode(10, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(8, OUTPUT);
  
  // The joystick button is always HIGH, unless the button is pressed
  digitalWrite(swPin, HIGH);
       
  display.begin();
  display.clearDisplay();
//  pinMode(LEFT,INPUT);    
//  pinMode(RIGHT,INPUT);
//  pinMode(UP,INPUT);
//  pinMode(DOWN,INPUT);
//  pinMode(PAUSE,INPUT);  
  pinMode(speakerPin,OUTPUT);  
//  digitalWrite(LEFT,HIGH);  
//  digitalWrite(RIGHT,HIGH);
//  digitalWrite(UP,HIGH);
//  digitalWrite(DOWN,HIGH);
//  digitalWrite(PAUSE,HIGH);
  display.setContrast(60);
  slength=8;             
  xegg=(display.width())/2;  
  yegg=(display.height())/2;  
  display.setTextSize(2);      
  display.setTextColor(BLACK);
  display.setCursor(0,5);
  display.print("Sarpili");
  display.setCursor(10,50);
  display.print("utopian");
  display.display();
  delay(4000);
  display.clearDisplay();  
  for(i=0;i<=slength;i++)  
  {
    x[i]=25-3*i;
    y[i]=10;   
  }  
  for(i=0;i<slength;i++)   
     {
     display.drawCircle(x[i],y[i],1,BLACK);
     }
     display.display();
dr=true;
}
void loop()   
{  
   movesnake();      
} 
void movesnake()
{
  xDirection = analogRead(VryPin);
  yDirection = analogRead(VrxPin);
//  l=digitalRead(LEFT);   
//  d=digitalRead(DOWN);
//  r=digitalRead(RIGHT);
//  u=digitalRead(UP);
  //p=digitalRead(PAUSE);  
if(flag==0)
{
direct();            
}
  if(millis()%time==0)   
{             
     if(flag==0)                                
     {
       if(dr==true){tempx=x[0]+3;tempy=y[0];}   
       if(dl==true){tempx=x[0]-3;tempy=y[0];}    
       if(du==true){tempy=y[0]-3;tempx=x[0];}
       if(dd==true){tempy=y[0]+3;tempx=x[0];}
     }   
   flag=0;  
     checkgame();                       
      checkegg();   
 if(tempx<=0){tempx=84+tempx;}    
 if(tempx>=84){tempx=tempx-84;}
 if(tempy<=0){tempy=48+tempy;}
 if(tempy>=48){tempy=tempy-48;}
 for(i=0;i<=slength;i++)      
  {
   xx=x[i];
   yy=y[i]; 
   x[i]=tempx;
   y[i]=tempy;
   tempx=xx;
   tempy=yy;
  }  
drawsnake();        
}
}
void checkgame()      
{
  for(i=1;i<slength;i++)            
  {
    if(x[i]==x[0] && y[i]==y[0])
    {      
      bh=EEPROM.read(1);
      bl=EEPROM.read(0);
      high=(((0xff00+bh)<<8) + bl);      
      if(score>high)
      {
        high=score;
        bh=(high >> 8);
        bl=high & 0xff;       
        EEPROM.write(1,bh);
        EEPROM.write(0,bl);
      }
      display.clearDisplay();
      display.setTextColor(BLACK);       
      display.setTextSize(1);
      display.setCursor(20,2);
      display.print("Ai murit");
      display.setCursor(20,12);
      display.print("fraiere");
      display.setCursor(15,30);
      display.print("Score: ");
      display.print(score);
      display.setCursor(15,40);
      display.print("High: ");
      display.print(high);     
      display.display();
      beep(20,5000);            
      display.clearDisplay();      
      slength=8;        
      score=0;
      time=280;      
      redraw();          
    }
  }
}
void checkegg()   
{
  if(x[0]==xegg or x[0]==(xegg+1) or x[0]==(xegg+2) or x[0]==(xegg-1))  
  {
    if(y[0]==yegg or y[0]==(yegg+1) or y[0]==(yegg+2) or y[0]==(yegg-1))
    {
      score+=1;                
      slength+=1;
      if(time>=90)
      {time-=20;}      
      display.fillRect(xegg,yegg,3,3,WHITE);          
      display.display();            
      beep(50,beeptime);          
      xegg=random(1,80);            
      yegg=random(1,40);
    }
  }
}        
void direct()             
{
  if(xDirection < 400 && dr==false)      
  {
    dl=true;du=false;dd=false;
    tempx=x[0]-3;          
    tempy=y[0];
    flag=1;
    digitalWrite(11, HIGH);
    digitalWrite(10, LOW);
    digitalWrite(9, LOW);
    digitalWrite(8, LOW);

  }  
  else if(xDirection > 600 && dl==false)
  {
    dr=true;du=false;dd=false;
    tempx=x[0]+3;
    tempy=y[0];
    flag=1;
    digitalWrite(11, LOW);
    digitalWrite(10, HIGH);
    digitalWrite(9, LOW);
    digitalWrite(8, LOW);
  }  
  else if(yDirection > 600 && dd==false)
  {
    du=true;dl=false;dr=false;
    tempy=y[0]-3;
    tempx=x[0];
    flag=1;
    digitalWrite(11, LOW);
    digitalWrite(10, LOW);
    digitalWrite(9, HIGH);
    digitalWrite(8, LOW);
  }  
  else if(yDirection < 400 && du==false)
  {
    dd=true;dl=false;dr=false;
    tempy=y[0]+3;
    tempx=x[0];
    flag=1;
    digitalWrite(11, LOW);
    digitalWrite(10, LOW);
    digitalWrite(9, LOW);
    digitalWrite(8, HIGH);
  } 
  else if(digitalRead(swPin)==LOW)
  {
  display.clearDisplay();  
  display.setTextColor(BLACK);
  for(i=5;i>0;i--)
    {
     display.setCursor(25,10); 
     display.setTextSize(1);
     display.print("RESET =))");
     display.setCursor(40,30);
     display.print(i);
     display.display();
     delay(1000);
     display.clearDisplay();
     slength=8;        
     score=0;
     time=280;
    } 
    redraw();    
  } 
}
void drawsnake()  
{
  display.fillRect(xegg,yegg,3,3,BLACK);  
  display.drawCircle(x[0],y[0],1,BLACK);  
  display.drawCircle(x[slength],y[slength],1,WHITE);
  display.display(); 
}
void redraw()
{
  display.fillRect(xegg,yegg,3,3,BLACK);
  for(i=0;i<slength;i++)
     {
     display.drawCircle(x[i],y[i],1,BLACK);
     }
     display.display();
}               
void beep (int freq,long tb)  
{ 
    int x;   
    long delayAmount = (long)(500/freq);   
    long loopTime = (long)(tb/(delayAmount*2)); 
    for (x=0;x<loopTime;x++)     // One iteration produces sound for one cycle
    {    
        digitalWrite(speakerPin,HIGH);
        delay(delayAmount);
        digitalWrite(speakerPin,LOW); 
        delay(delayAmount);
    }        
    delay(2);   
}
