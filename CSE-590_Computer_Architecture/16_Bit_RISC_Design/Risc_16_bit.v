`timescale 1ns / 1ps

// only clk is the input, no other signals can be there.
module Risc_16_bit(
	input clk
);

wire [3:0] read_INSTRUCTION;
wire [15:0] INSTRUCTION;
wire [3:0] COUNTER;
	
Datapath_Unit DU(
	clk,
	read_INSTRUCTION,
	INSTRUCTION,
	COUNTER
);

Control_Unit CU(
	clk,
	read_INSTRUCTION,
	INSTRUCTION,
	COUNTER
	);

initial
	begin
		$display("Inside Risc_16_bit");
	end

endmodule













