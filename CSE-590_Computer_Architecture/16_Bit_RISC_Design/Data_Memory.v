`timescale 1ns / 1ps
`include "Parameter.v"


module Data_Memory(
	input clk,
	input LOAD,
	input STORE
);

reg [`col - 1:0] memory [`row_d - 1:0];
integer f;

initial
	begin
		$readmemb("test\\test.data", memory);
		f = $fopen(`filename);
		$fmonitor(f, "time = %d\n", $time, 
		"\tmemory[0] = %b\n", memory[0],			
		"\tmemory[1] = %b\n", memory[1],
		"\tmemory[2] = %b\n", memory[2],
		"\tmemory[3] = %b\n", memory[3],
		"\tmemory[4] = %b\n", memory[4],
		"\tmemory[5] = %b\n", memory[5],
		"\tmemory[6] = %b\n", memory[6],
		"\tmemory[7] = %b\n", memory[7]);
		`simulation_time;
		$fclose(f);
		
		$display("Memory Loaded");
		
	end
	
endmodule












