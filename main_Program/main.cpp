#include <chrono>
#include <thread>
#include <string>
using namespace std::chrono_literals;
#include <cpprest/http_msg.h>
#include <wiringPi.h>
#define LED_RED      17
#define BUTTON_TIMER 24
#define BUTTON_LED   27
#define BUZZER       18
#define RG_GREEN     20
#define RG_RED       21

int main() {
	wiringPiSetupGpio();
	pinMode(LED_RED, OUTPUT);
	pinMode(BUZZER, OUTPUT);
	pinMode(RG_RED, OUTPUT);
	pinMode(RG_GREEN, OUTPUT);
	pinMode(BUTTON_LED, OUTPUT);
	pinMode(BUTTON_TIMER, INPUT);

	while (true) {
			std::cout<<"----Menu------\n";
			std::cout<<"1.BlinkLED(blink the led for input number of times)\n";
			std::cout<<"2.Button(press button to light up led with input time limit)\n";
			std::cout<<"3.Buzzer ring(ring the buzzer for input number of times)\n";
			std::cout<<"4.Two-color-led blink(blink the colorled for input number of times)\n";
			std::cout<<"Enter service number...\n";
			int serN;
			std::cin >> serN;
			switch (serN) {
				case 1:{
					std::cout<<"enter the time you want the led to blink...\n";
					int blinktime;
					std::cin >> blinktime;
					for(int ii = blinktime; ii > 0; ii--){
						digitalWrite(LED_RED, HIGH);
						std::clog<<"blink, ";
						std::this_thread::sleep_for(0.2s);
						digitalWrite(LED_RED, LOW);
						std::this_thread::sleep_for(0.5s);
					}
					std::cout<<" ...blink finished...\n";
					delay(1000);
					break;
				}
				case 2:{
					std::cout<<"enter the time you want the buttonled to light(ms)...\n";
					int buttonledl;
					std::cin >> buttonledl;
			  	std::cout<<"press the button to light up the led...\n";
					while(buttonledl>0){
						if(digitalRead(BUTTON_TIMER) == HIGH){
							digitalWrite(BUTTON_LED, HIGH);
							delay(buttonledl);
							digitalWrite(BUTTON_LED, LOW);
							std::cout<<"time up, close buttonled\n";
							buttonledl = 0;
						}
					}
					delay(1000);
					break;
				}
				case 3:{
					std::cout<<"enter the time you want the buzzer to ring...\n";
					int buzzerring;
					std::cin >> buzzerring;
					for(int ii = buzzerring; ii > 0; ii--){
						digitalWrite(BUZZER, HIGH);
						std::clog<<"ring, ";
						delay(200);
						digitalWrite(BUZZER, LOW);
						delay(500);
					}
					std::cout<<" ...buzzer finished...\n";
					delay(1000);
					break;
				}
				case 4:{
					std::cout<<"enter the time you want the 2-color led to blink...\n";
					int blinktime2;
					std::cin >> blinktime2;
					for(int ii = blinktime2; ii > 0; ii--){
						digitalWrite(RG_RED, HIGH);
						std::clog<<"blink, ";
						delay(200);
						digitalWrite(RG_RED, LOW);
						delay(100);
						digitalWrite(RG_GREEN, HIGH);
						delay(200);
						digitalWrite(RG_GREEN, LOW);
						delay(100);
					}
					std::cout<<" ...blink 2-color finished...\n";
					delay(1000);
					break;
				}
				default:{
					std::cout<<"invalid input, enter your service number again...\n";
					delay(1000);
					break;
				}
			}
	}
	return 0;
}
