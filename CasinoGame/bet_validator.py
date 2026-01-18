"""
Lucky Queen Casino - Betting Validation Module

Validates wager inputs against:
• Player balance
• Minimum and maximum bet limits
• Optional session loss caps

This module is designed for integration with the Java-based casino game engine.
"""

import sys

def validate_bet(bet, balance, min_bet, max_bet, session_net=0.0, loss_limit=float("inf")):
    """
    bet: wager the user is trying to place
    balance: current available balance
    min_bet, max_bet: table limits
    session_net: deposits - withdrawals for this session
    loss_limit: max loss allowed before blocking bets
    """

    # Block if player exceeded allowed session loss
    if session_net <= -abs(loss_limit):
        return False, "Session loss limit reached. Betting disabled."

    # Ensure bet is numeric and positive
    try:
        bet = float(bet)
    except ValueError:
        return False, "Invalid bet format."

    if bet <= 0:
        return False, "Bet must be greater than 0."

    # Enforce table limits
    if bet < min_bet:
        return False, f"Bet must be at least {min_bet}."
    if bet > max_bet:
        return False, f"Bet must be at most {max_bet}."

    # Check balance availability
    if bet > balance:
        return False, "Insufficient balance for this wager."

    return True, "Bet approved."


if __name__ == "__main__":
    # Command line usage: python bet_validator.py bet balance min max [sessionNet] [lossLimit]
    if len(sys.argv) < 5:
        print("DENIED")
        print("Usage: bet balance min max [sessionNet] [lossLimit]")
        sys.exit()

    bet = sys.argv[1]
    balance = float(sys.argv[2])
    min_bet = float(sys.argv[3])
    max_bet = float(sys.argv[4])
    session_net = float(sys.argv[5]) if len(sys.argv) > 5 else 0.0
    loss_limit = float(sys.argv[6]) if len(sys.argv) > 6 else float("inf")

    ok, message = validate_bet(bet, balance, min_bet, max_bet, session_net, loss_limit)

    print("APPROVED" if ok else "DENIED")
    print(message)
