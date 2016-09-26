`timescale 1ns / 1ps
`include "Parameter.v"

module Instruction_Memory(
	input clk
);

reg [`col - 1:0] inst_memory [`row_i - 1:0];

initial 
	begin
		$readmemb("test\\test.prog", memory);
		INSTRUCTION[`col - 1:0] = 16'bXXXXXXXXXXXXXXXX; //set to empty instruction
	end
	
endmodule













