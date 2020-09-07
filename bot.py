import os
import requests
import random
import mebots

from flask import Flask, request

app = Flask(__name__)
bot = mebots.Bot('eightball', os.environ.get('BOT_TOKEN'))
website = Website()

PREFIX = '?'

OPTIONS = [
    'It is certain.', 'It is decidedly so.', 'Without a doubt.', 'Yes - definitely.', 'You may rely on it.',
    'As I see it, yes.', 'Most likely.', 'Outlook good.', 'Yes.', 'Signs point to yes.',
    'Reply hazy, try again.', 'Ask again later.', 'Better not tell you now.', 'Cannot predict now.', 'Concentrate and ask again.',
    'Don\'t count on it.', 'My reply is no.', 'My sources say no.', 'Outlook not so good.', 'Very doubtful.'
]

def process(message):
    # Prevent self-reply
    if message['sender_type'] != 'bot':
        if message['text'].startswith(PREFIX):
            return random.choice(OPTIONS)

# Endpoint
@app.route('/', methods=['POST'])
def receive():
    message = request.get_json()
    group_id = message['group_id']
    response = process(message)
    if response:
        send(response, group_id)

    return 'ok', 200


def send(text, group_id):
    url  = 'https://api.groupme.com/v3/bots/post'

    message = {
        'bot_id': bot.instance(group_id).id,
        'text': text,
    }
    r = requests.post(url, data=message)


if __name__ == '__main__':
    while True:
        print(process({'text': input('> '), 'sender_type': 'user', 'group_id': None}))
