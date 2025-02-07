package CardAugments.cardmods.rare;

import CardAugments.CardAugmentsMod;
import CardAugments.actions.CultistAction;
import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class CultistMod extends AbstractAugment {
    public static final String ID = CardAugmentsMod.makeID("CultistMod");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    private static final int CARDS = 1;

    @Override
    public void onInitialApplication(AbstractCard card) {
        modifyBaseStat(card, BuffType.DAMAGE, BuffScale.MINOR_DEBUFF);
    }

    @Override
    public boolean validCard(AbstractCard card) {
        return card.baseDamage > 1 && card.type == AbstractCard.CardType.ATTACK;
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return TEXT[0] + cardName + TEXT[1];
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + String.format(TEXT[2], CARDS);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        addToBot(new CultistAction());
    }

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.RARE;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CultistMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }
}
