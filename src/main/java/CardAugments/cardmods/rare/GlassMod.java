package CardAugments.cardmods.rare;

import CardAugments.CardAugmentsMod;
import CardAugments.cardmods.AbstractAugment;
import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class GlassMod extends AbstractAugment {
    public static final String ID = CardAugmentsMod.makeID("GlassMod");
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(ID).TEXT;

    private static final int NERF = 2;

    @Override
    public void onInitialApplication(AbstractCard card) {
        modifyBaseStat(card, BuffType.DAMAGE, BuffScale.HUGE_BUFF);
    }

    @Override
    public boolean validCard(AbstractCard card) {
        return card.type == AbstractCard.CardType.ATTACK && card.baseDamage > 0 && cardDoesntExhaust(card);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        this.addToBot(new ModifyDamageAction(card.uuid, -NERF));
    }

    @Override
    public String modifyName(String cardName, AbstractCard card) {
        return TEXT[0] + cardName + TEXT[1];
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (rawDescription.contains(TEXT[4])) {
            return rawDescription.replace(TEXT[4], TEXT[5]);
        }
        return rawDescription + String.format(TEXT[2], NERF);
    }

    @Override
    public AugmentRarity getModRarity() {
        return AugmentRarity.RARE;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GlassMod();
    }

    @Override
    public String identifier(AbstractCard card) {
        return ID;
    }

}
