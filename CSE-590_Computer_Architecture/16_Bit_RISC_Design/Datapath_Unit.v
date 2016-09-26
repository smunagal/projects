`timescale 1ns / 1ps
`include "Parameter.v"

module Datapath_Unit(
	input clk,
	input [3:0]read_INSTRUCTION,
	output reg [15:0] INSTRUCTION,
	output reg [3:0] COUNTER
);

reg [15:0] R [7:0];
reg [`col - 1:0] inst_memory [`row_i - 1:0];
reg [3:0] inst_count = 0;


initial
	begin
		COUNTER =0;
		$display("COUNTER INITIALIZED");
	end

always@(read_INSTRUCTION) begin
		//$display("INSIDE DATA PATH UNIT");
		$readmemb("test\\test.prog", inst_memory);
		INSTRUCTION = inst_memory[inst_count];
		if(INSTRUCTION !== 16'bxxxxxxxxxxxxxxxx) begin
			inst_count = inst_count + 1;
			COUNTER = COUNTER + 1;
			//$display("CURRENT INST COUNT  : %d , INSTRUCTION  :%b", COUNTER, INSTRUCTION);
			//$display("COUNTER INCREMENTED : %d", COUNTER);
		end
	end
	
endmodule














